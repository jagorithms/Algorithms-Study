-- 코드를 입력하세요
SELECT ai.animal_id, ai.name
from animal_ins ai
join animal_outs ao on ai.animal_id = ao.animal_id
where ao.datetime < ai.datetime 
order by ai.datetime
