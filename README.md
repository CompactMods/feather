# feather: a typed Java graph library

# TODO/Functional Requirements

- [ ] Nodes
  - [ ] A `Node` has a set of known `features`.
  - [ ] -
- [ ] Node Features
  - [x] A node `feature` is static, defining a characteristic of a node.
  - [ ] -
- [ ] Traversal/Edges
  - [ ] A `node` may connect to another `node`, via an `edge`.
  - [ ] A node's `property` may connect to another `property` via an `edge`.
  - [ ] An edge may either be `empty` or have a data `schema` defined.
    - [ ] An empty edge stores no data, only its endpoints.
    - [ ] An edge with a data `schema` may have non-computed data attached.

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