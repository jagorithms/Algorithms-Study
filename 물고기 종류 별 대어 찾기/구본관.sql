with sub as (
    select FNI.FISH_TYPE, MAX(LENGTH)
    from FISH_INFO FI  join FISH_NAME_INFO FNI on FI.FISH_TYPE = FNI.FISH_TYPE
    group by FI.FISH_TYPE
)

select FI.ID, FNI.FISH_NAME, FI.LENGTH
from FISH_INFO FI  join FISH_NAME_INFO FNI on FI.FISH_TYPE = FNI.FISH_TYPE
where (FNI.FISH_TYPE, FI.LENGTH) in 
(
    select *
    from sub
)
