SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS ter_pos_terminal;
DROP TABLE IF EXISTS ter_bill_day;
DROP TABLE IF EXISTS ter_merchant;



/* Create Tables */

CREATE TABLE ter_pos_terminal
(
	id varchar(64) NOT NULL COMMENT '编号',

	import_date DATE COMMENT '进件日期',
	down_date DATE COMMENT '下机日期',
	install_date DATE COMMENT '装机日期',
	trans_bank VARCHAR(20) COMMENT '交件支行',
	device_num VARCHAR(25) COMMENT '机身号',
	device_type VARCHAR(20) COMMENT '机子类型',
	merchant_num VARCHAR(20) NOT NULL COMMENT '商户号,重要字段',
	termial_num VARCHAR(20) NOT NULL COMMENT '终端号,重要字段',
  wechat_url VARCHAR(512) COMMENT '微信二维码',
  business_license VARCHAR(512) COMMENT '营业执照',
  merchant_name VARCHAR(100) COMMENT '商户名称',
  merchant_address VARCHAR(512) COMMENT '地址',
  merchant_legal_person VARCHAR(10) COMMENT '法人',
  booking_person VARCHAR(10) COMMENT '入帐人',
  telphone VARCHAR(18) COMMENT '联系电话',
  install_phone VARCHAR(18) COMMENT '装机电话',
  device_mcc VARCHAR(10) COMMENT 'MCC码',
  debit_rate VARCHAR(20) COMMENT '借记卡费率',
  credit_rate VARCHAR(20) COMMENT '贷记卡费率',
  foreign_rate VARCHAR(20) COMMENT '外币卡费率',
  machine_type VARCHAR(20) COMMENT '机具类型',
  id_card VARCHAR(18) COMMENT '身份证号',
  bank_card VARCHAR(100) COMMENT '银行卡号',
  bank_card_account_bank VARCHAR(18) COMMENT '银行卡开户行',
	user_id VARCHAR(64) NOT NULL COMMENT '所属用户,重要字段',
  salesman VARCHAR(20) COMMENT '业务员',
  terminal_desc TEXT COMMENT '详情',

	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = 'POS终端设备表';


CREATE TABLE ter_merchant
(
	id varchar(64) NOT NULL COMMENT '编号',

	merchant_num VARCHAR(20) NOT NULL COMMENT '商户号',
  wechat_url VARCHAR(512) COMMENT '微信二维码',
  business_license VARCHAR(512) COMMENT '营业执照',
  merchant_name VARCHAR(100) COMMENT '商户名称',
  merchant_address VARCHAR(512) COMMENT '地址',
  merchant_legal_person VARCHAR(10) COMMENT '法人',
  booking_person VARCHAR(10) COMMENT '入帐人',
  telphone VARCHAR(18) COMMENT '联系电话',
  debit_rate VARCHAR(20) COMMENT '借记卡费率',
  credit_rate VARCHAR(20) COMMENT '贷记卡费率',
  foreign_rate VARCHAR(20) COMMENT '外币卡费率',
  id_card VARCHAR(18) COMMENT '身份证号',
  bank_card VARCHAR(100) COMMENT '银行卡号',
  bank_card_account_bank VARCHAR(18) COMMENT '银行卡开户行',
	user_id VARCHAR(64) NOT NULL COMMENT '所属用户',
  merchat_desc TEXT COMMENT '详情',

	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = '商户表';
alter table ter_merchant add unique(merchant_num)


CREATE TABLE ter_bill_day
(
	id varchar(64) NOT NULL COMMENT '编号',

  clear_date DATE NOT NULL COMMENT '清算日期',
  tran_code VARCHAR(10) NOT NULL COMMENT '交易代码',
  handle_code VARCHAR(10) COMMENT '受理流水',
  tran_date_time VARCHAR(12) COMMENT '交易日期时间,EG:0613161722',
  card_no VARCHAR(20) NOT NULL COMMENT '卡号',
  tran_amount DOUBLE NOT NULL COMMENT '交易金额',
  refer_code VARCHAR(20) COMMENT '参考号,EG:000313002980',
  grant_code VARCHAR(10) COMMENT '授权码',
  terminal_num VARCHAR(20) NOT NULL COMMENT '终端号,重要字段',
  merchant_num VARCHAR(20) NOT NULL COMMENT '商户编号,重要字段',
  merchant_name VARCHAR(100) COMMENT '商户名称',
  debit_fee DOUBLE COMMENT '商户借记手续费',
  credit_fee DOUBLE COMMENT '商户贷记手续费',

	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = '终端日流水表';
create index day_idx on ter_bill_day(clear_date);

