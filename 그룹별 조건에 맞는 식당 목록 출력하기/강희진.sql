-- 코드를 입력하세요
with mem as
(
    select member_id
    from rest_review
    group by member_id
    order by count(*) desc
    limit 1
)

SELECT MEMBER_NAME, REVIEW_TEXT, date_format(review_date, '%Y-%m-%d') as REVIEW_DATE
from rest_review
inner join member_profile mp on mp.member_id = rest_review.member_id
inner join mem on mem.member_id = rest_review.member_id
where rest_review.member_id = mem.member_id
order by review_date asc, review_text asc
