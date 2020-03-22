import {
   OPCUAClient,
   MessageSecurityMode, SecurityPolicy,
   AttributeIds,
   makeBrowsePath,
   ClientSubscription,
   TimestampsToReturn,
   MonitoringParametersOptions,
   ReadValueIdLike,
   ClientMonitoredItem,
   DataValue,
   ClientMonitoredItemGroup,
   ClientMonitoredItemBase,
   NodeClass,
   resolveNodeId
} from "node-opcua";

import axios from "axios";

const connectionStrategy = {
    initialDelay: 1000,
    maxRetry: 1
}

const options = {
    applicationName: "OpcClient",
    connectionStrategy: connectionStrategy,
    securityMode: MessageSecurityMode.None,
    securityPolicy: SecurityPolicy.None,
    endpoint_must_exist: false,
};

const nodeId = "ns=3;s=85/0:Simulation"; // RootFolder.Objects.Server
const endpointUrl = "opc.tcp://DESKTOP-59V4KCK:53530/OPCUA/SimulationServer";

const samplerInterval = 1000
const refreshBufferInterval = 2000
const INFINITY = 999999999

const client = OPCUAClient.create(options);

async function main() {
   await client.connect(endpointUrl);
   let session = await client.createSession();

   let nodeIdes = await fillNodeId(session)
   console.log(nodeIdes)

   const subscription = ClientSubscription.create(session, {
      requestedPublishingInterval: 1000,
      requestedLifetimeCount:      100,
      requestedMaxKeepAliveCount:   10,
      maxNotificationsPerPublish:  100,
      publishingEnabled: true,
      priority: 10
   });

   subscription.on("started", function() {
      console.log("subscription started for 2 seconds - subscriptionId=", subscription.subscriptionId);
   }).on("keepalive", function() {
      console.log("keepalive");
   }).on("terminated", function() {
      console.log("terminated");
   });

   let array = []
   nodeIdes.forEach((elem) => {
      console.log(elem)
      array.push({
         nodeId: resolveNodeId(elem),
         attributeId: AttributeIds.Value
      })
   })


   const itemToMonitor: ReadValueIdLike = {
      nodeId: "ns=3;s=3521",
      attributeId: AttributeIds.Value
   };

   const parameters: MonitoringParametersOptions = {
      samplingInterval: samplerInterval,
      discardOldest: true,
      queueSize: 10
   };
    
   const monitoredItem = ClientMonitoredItemGroup.create(
      subscription,
      array,
      parameters,
      TimestampsToReturn.Both
   );

   let buffer = []
   setInterval(function sendMetrics() {
      axios.post('http://localhost:8110/test', buffer)
      buffer = []
   }, refreshBufferInterval);

   monitoredItem.on("changed", (monitoredItem: ClientMonitoredItemBase, dataValue: DataValue, index: number) => {
       console.log(" value has changed : ", dataValue.value.toString(), " | ", monitoredItem.itemToMonitor.nodeId.value, " | ", Math.round(dataValue.sourceTimestamp.getTime() / 1000));
       buffer.push({
          id: monitoredItem.itemToMonitor.nodeId.value,
          value: dataValue.value.value,
          timestamp: Math.round(dataValue.sourceTimestamp.getTime() / 1000)
       });
   });

   await new Promise(resolve => setTimeout(resolve, INFINITY));

    console.log("now terminating subscription");
    await subscription.terminate();
    await session.close();

}

async function fillNodeId(session) {

   let browseResult = await session.browse({
      nodeId,
      nodeClassMask: NodeClass.Variable,
      resultMask: 63
   });

   return browseResult['references'].map((elem) => {
     return elem['nodeId'].toString()
   });  
};

main();