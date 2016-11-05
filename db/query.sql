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


-- 查出商户插入商户表
select DISTINCT
  merchant_num,
  wechat_url,
  business_license,
  merchant_name,
  merchant_address,
  merchant_legal_person,
  booking_person,
  telphone,
  debit_rate,
  credit_rate,
  foreign_rate,
  id_card,
  bank_card,
  bank_card_account_bank,
	user_id
from ter_pos_terminal


where merchant_num in(
  select merchant_num from ter_pos_terminal
  group by merchant_num
)


--findListByTerIds 查找商户
select DISTINCT
  merchant_num,
  wechat_url,
  business_license,
  merchant_name,
  merchant_address,
  merchant_legal_person,
  booking_person,
  telphone,
  debit_rate,
  credit_rate,
  foreign_rate,
  id_card,
  bank_card,
  bank_card_account_bank,
	user_id
from ter_merchant a
INNER JOIN ter_pos_terminal b
ON a.merchant_num = b.merchant_num
WHERE b.id IN (

)




