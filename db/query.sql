-- 查询过程：先取User的company的id和parent_ids，拼接起来，再取此compny_id下的所有用户id
-- 查询某公司下的所有终端ID。
select *
from ter_pos_terminal
where user_id in(
select id from
sys_user where company_id in
(select id from sys_office where parent_ids like
'0,1,a700c13a0a914d4ca99b68042001c274%'))


select * from ter_pos_terminal pos_tb
left join
(select u.id as uid
from sys_user u
left join
sys_office s
on u.company_id = s.id
where s.parent_ids like
'0,1,a700c13a0a914d4ca99b68042001c274%'
)
user_tb
on pos_tb.user_id = user_tb.uid

