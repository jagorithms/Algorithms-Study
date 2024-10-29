with sub1 as (
    select car_id, discount_rate
    from CAR_RENTAL_COMPANY_DISCOUNT_PLAN p join CAR_RENTAL_COMPANY_CAR c
    on p.car_type = c.car_type
    where duration_type = "30일 이상" and (p.car_type = "세단" or p.car_type = "SUV")
),
sub2 as (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY  
    where not (end_date < "2022-11-01" or start_date > "2022-11-30")
)
select c.car_id, car_type, round(daily_fee * 30 * (100 - discount_rate) / 100, 0) as FEE
from CAR_RENTAL_COMPANY_CAR c
join sub1 on c.car_id = sub1.car_id
where c.car_id not in (select * from sub2)
and 500000 <= round(daily_fee * 30 * (100 - discount_rate) / 100, 0) 
and round(daily_fee * 30 * (100 - discount_rate) / 100, 0) < 2000000
order by FEE desc, car_type, c.car_id desc;




/* 
CAR_RENTAL_COMPANY_CAR
car_id	car_type	daily_fee	     options
   1	   트럭	     102000	  주차감지센서,열선시트

CAR_RENTAL_COMPANY_RENTAL_HISTORY
history_id	car_id	    start_date	         end_date
    506	      15	2022-08-01 00:00:00	2022-08-02 00:00:00

CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
plan_id	car_type	duration_type	discount_rate
    1	  세단	      7일 이상	          5
*/
