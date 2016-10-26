SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS ter_pos_terminal;





/* Create Tables */

CREATE TABLE ter_pos_terminal
(
	id varchar(64) NOT NULL COMMENT '编号',

	terminal_num varchar(15) NOT NULL COMMENT '终端编号',

	user_id VARCHAR(64) NOT NULL COMMENT '所属用户',

	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = 'POS终端设备表';