MATCH (s:Scientist {born: 1850})-[:RESEARCHED]->
    (sc:Science)<-[i:INVENTED_BY {year: 560}]-(p:Pioneer {born: 525})-[:LIVES_IN]->
    (c:City)-[:PART_OF]->(cc:Country {formed: 411})

  (s)->(sc)<-(p)->(c)->(cc)

RETURN s.born, s.name, p.name, cc.formed;