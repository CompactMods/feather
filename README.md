# feather

# Goal
1. Create a schema for a node. A node is a composition of several components.
   - Data: A data node holds data properties, and may expose these properties through connections.
   - Logic: A logical node applies logical functions and must have at least 1 outbound connection.


# Requirements

A `NodeSystem` is composed of `Nodes` and their various *components*.

A `Node` may hold several data `properties`, and may `reference` other nodes.

Each data `property` has a `schema`, and each `schema` defines the data structure for the property.

---

Output Data Only:
```   
[CompactMachine: DataOutputNode]  
location: GlobalPos        --> 1    // ComplexDataProperty
   dimension: Dimension    --> 2    // ComplexDataProperty
      id: ResourceLocation --> 3
   position: BlockPos      --> 2

<GlobalPos: ComplexDataProperty>
position:   BlockPos       --> 1    
dimension:  Dimension      --> 1    // ComplexDataProperty
   id: ResourceLocation    --> 2
         
<Dimension: ComplexDataProperty>    
id: ResourceLocation       --> 1
```

Logical:
```
   [GlobalPos] --> [RelativePos::up] --> [GlobalPos]
   
   [GlobalPos] --> [Multicast] --> [GlobalPos]
                               --> [GlobalPos]
```

Input Data Only:
```
[TreefellUpgrade: DataInputNode]
-->   axeStorage: GlobalPos
-->   outputs: GlobalPos   
```