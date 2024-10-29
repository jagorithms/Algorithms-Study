SELECT cr.car_id, cr.car_type, round(30 * daily_fee * (1-0.01*discount_rate)) as fee
from CAR_RENTAL_COMPANY_CAR cr 
join CAR_RENTAL_COMPANY_RENTAL_HISTORY crh on cr.car_id = crh.car_id
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN cdp on cdp.car_type = cr.car_type
where cr.car_type in ('세단', 'SUV') 
 and (
     cr.car_id not in (
        select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY
         where start_date <= '2022-11-30' or end_date >= '2022-11-01'
    ) and duration_type like '30%'
)
group by car_id
having  30 * daily_fee * (1-0.01*discount_rate) >= 500000 
 and 30 * daily_fee * (1-0.01*discount_rate) <= 2000000
order by fee desc, cr.car_type asc, cr.car_id desc;
