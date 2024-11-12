select id, fni.fish_name, length from fish_info join fish_name_info fni on fish_info.fish_type = fni.fish_type
where (fni.fish_type, length) in (
    select fish_type, max(length)
    from fish_info
    group by fish_type
)
order by id asc;
