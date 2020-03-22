const { OPCUAClient, NodeClass } = require("node-opcua");

const nodeId = "ns=0;i=2253"; // RootFolder.Objects.Server
const endpointUri = "opc.tcp://DESKTOP-59V4KCK:53530/OPCUA/SimulationServer";

(async () => {

    const client = OPCUAClient.create({ endpoint_must_exist: false});
    client.on("backoff", () => console.log("Backoff: trying to connect to ", endpointUri));

    await client.withSessionAsync(endpointUri, async (session) => {
        let browseResult = await session.browse({
            nodeId,
            nodeClassMask: NodeClass.Variable, // we only want sub node that are Variables
            resultMask: 63 // extract all information possible 
        });
        console.log("BrowseResult = ", browseResult.toString());
    });
})();