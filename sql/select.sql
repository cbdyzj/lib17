SELECT
    t.name,
    t.age
FROM (SELECT
        ROW_NUMBER() OVER (PARTITION BY age
        ORDER BY age) AS ranker,
        name,
        age
        FROM student) AS t
WHERE ranker = 1
LIMIT 20;
