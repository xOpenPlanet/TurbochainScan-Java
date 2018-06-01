SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accounts_transaction
-- ----------------------------
DROP TABLE IF EXISTS `accounts_transaction`;
CREATE TABLE `accounts_transaction` (
  `txn_address` varchar(68) NOT NULL,
  `sender` varchar(44) DEFAULT NULL,
  `receiver` varchar(44) DEFAULT NULL,
  `txn_timestamp` bigint(20) DEFAULT NULL,
  `txn_type` int(2) DEFAULT NULL COMMENT '1、外部账户交易2、外部账户调用合约账户3、外部账户创建合约',
  PRIMARY KEY (`txn_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for blocks
-- ----------------------------
DROP TABLE IF EXISTS `blocks`;
CREATE TABLE `blocks` (
  `number` varchar(20) NOT NULL,
  `hash` varchar(68) DEFAULT NULL,
  `parent_hash` varchar(68) DEFAULT NULL,
  `nonce` varchar(20) DEFAULT NULL,
  `sha3_uncles` varchar(68) DEFAULT NULL,
  `logs_bloom` text,
  `transactions_root` varchar(68) DEFAULT NULL,
  `state_root` varchar(68) DEFAULT NULL,
  `receipts_root` varchar(68) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `miner` varchar(44) DEFAULT NULL,
  `mix_hash` varchar(68) DEFAULT NULL,
  `difficulty` varchar(40) DEFAULT NULL,
  `total_difficulty` varchar(40) DEFAULT NULL,
  `extra_data` varchar(255) DEFAULT NULL,
  `size` varchar(40) DEFAULT NULL,
  `gas_limit` varchar(30) DEFAULT NULL,
  `gas_used` varchar(30) DEFAULT NULL,
  `timestamp` varchar(20) DEFAULT NULL,
  `transactions_size` int(10) DEFAULT NULL,
  `uncles_size` int(10) DEFAULT NULL,
  `sealfields_size` int(10) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contracts
-- ----------------------------
DROP TABLE IF EXISTS `contracts`;
CREATE TABLE `contracts` (
  `address` varchar(44) NOT NULL,
  `codes` text,
  `creater` varchar(44) DEFAULT NULL,
  `block_hash` varchar(68) DEFAULT NULL,
  `block_number` varchar(20) DEFAULT NULL,
  `transaction_hash` varchar(68) DEFAULT NULL,
  PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ether_status
-- ----------------------------
DROP TABLE IF EXISTS `ether_status`;
CREATE TABLE `ether_status` (
  `number` varchar(20) NOT NULL,
  `start_timestamp` varchar(20) DEFAULT NULL,
  `end_timestamp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for timer
-- ----------------------------
DROP TABLE IF EXISTS `timer`;
CREATE TABLE `timer` (
  `id` int(11) NOT NULL,
  `current_block_height` varchar(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `timer` VALUES ('1', '0');

-- ----------------------------
-- Table structure for transactions
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `hash` varchar(68) NOT NULL,
  `nonce` varchar(40) DEFAULT NULL,
  `block_hash` varchar(68) NOT NULL,
  `block_number` varchar(20) NOT NULL,
  `transaction_index` varchar(40) DEFAULT NULL,
  `sender` varchar(44) DEFAULT NULL,
  `receiver` varchar(44) DEFAULT '',
  `value` varchar(30) DEFAULT NULL,
  `gas_price` varchar(30) DEFAULT '',
  `gas` varchar(30) DEFAULT '0',
  `input` text,
  `creates` varchar(255) DEFAULT NULL,
  `public_key` varchar(128) DEFAULT NULL,
  `raw` varchar(128) DEFAULT NULL,
  `r` varchar(255) DEFAULT NULL,
  `s` varchar(255) DEFAULT NULL,
  `v` int(20) DEFAULT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users_account
-- ----------------------------
DROP TABLE IF EXISTS `users_account`;
CREATE TABLE `users_account` (
  `address` varchar(44) NOT NULL,
  PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
