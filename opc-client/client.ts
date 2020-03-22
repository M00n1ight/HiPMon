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
   NodeClass 
} from "node-opcua";

const connectionStrategy = {
    initialDelay: 1000,
    maxRetry: 1
}

const options = {
    applicationName: "MyClient",
    connectionStrategy: connectionStrategy,
    securityMode: MessageSecurityMode.None,
    securityPolicy: SecurityPolicy.None,
    endpoint_must_exist: false,
};


const client = OPCUAClient.create(options);

const endpointUrl = "opc.tcp://DESKTOP-59V4KCK:53530/OPCUA/SimulationServer";

async function main() {
  
  try {
    // step 1 : connect to
        await client.connect(endpointUrl);
        console.log("connected !");

        // client.getEndpoints().then(
        //       result => console.log(result)
        //    )

    // step 2 : createSession
        const session = await client.createSession();
        console.log("session created !");

    // step 3 : browse

        const browseResult = await session.browse("RootFolder");
    
        console.log("references of RootFolder :");
        for(const reference of browseResult.references) {
           console.log(JSON.stringify(reference))
            console.log( "   -> ", reference.browseName.toString());
        }
    

    // step 4 : read a variable with readVariableValue
        const dataValue2 = await session.readVariableValue("ns=3;s=3521");
        console.log(" value = " , dataValue2.toString());

    // step 4' : read a variable with read
        const maxAge = 0;
        const nodeToRead = {
          nodeId: "ns=3;s=3521",
          attributeId: AttributeIds.Value
        };
        const dataValue =  await session.read(nodeToRead, maxAge);
        console.log(" value " , dataValue.toString());

    // step 5: install a subscription and install a monitored item for 10 seconds
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
    
    
    // install monitored item
    
    const itemToMonitor: ReadValueIdLike = {
        nodeId: "ns=3;s=3521",
        attributeId: AttributeIds.Value
    };
    const parameters: MonitoringParametersOptions = {
        samplingInterval: 100,
        discardOldest: true,
        queueSize: 10
    };
    
    const monitoredItem  = ClientMonitoredItem.create(
        subscription,
        itemToMonitor,
        parameters,
        TimestampsToReturn.Both
    );
    
    monitoredItem.on("changed", (dataValue: DataValue) => {
       console.log(" value has changed : ", dataValue.value.toString());
    });
    
    
    await new Promise(resolve => setTimeout(resolve, 2000));
    // async function timeout(ms: number) {
    //     return new Promise(resolve => setTimeout(resolve, ms));
    // }
    // await timeout(10000);
    
    console.log("now terminating subscription");
    await subscription.terminate();
    
    //
    const nodeId = "ns=0;i=2253"; // RootFolder.Objects.Server
    await client.withSessionAsync(endpointUrl, async (session) => {
        let browseResult = await session.browse({
            nodeId,
            nodeClassMask: NodeClass.Variable, // we only want sub node that are Variables
            resultMask: 63 // extract all information possible 
        });
        console.log("BrowseResult = ", browseResult.toString());
    });
    //

    // step 6: finding the nodeId of a node by Browse name
        const browsePath = makeBrowsePath("RootFolder", "/Objects/Simulation");

        const result = await session.translateBrowsePath(browsePath);

        console.log("result" + JSON.stringify(result))

        const productNameNodeId = result.targets[0].targetId;
        console.log(" Product Name nodeId = ", productNameNodeId.toString());

    // close session
        await session.close();

    // disconnecting
        //await client.disconnect();
        console.log("done !");
  } catch(err) {
    console.log("An error has occured : ",err);
  }
}
main();