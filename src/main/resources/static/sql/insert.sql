SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('犬用', '犬用グッズのカテゴリーです。'),
('猫用', '猫用グッズのカテゴリーです。'),
('鳥用', '小鳥用グッズのカテゴリーです。');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company)VALUES 
('ドッグフード','どっぐふーど','犬用の餌です。',1,2000,'/img/dog_food.jpg','2026/02/27','ユニアップ'),
('キャットフード','きゃっとふーど','猫用の餌です。',2,2000,'/img/cat_food.jpg','2026/02/27','ユニアップ'),
('鳥の餌','とりのえさ','鳥用の餌です。',3,1200,'/img/bird_food.jpg','2026/02/27','まどろみペット'),
('リード','りーど','犬の散歩用リードです。',1,1100,'/img/dog_lead.jpg','2026/02/27','まどろみペット'),
('けりぐるみ','けりぐるみ','猫が抱え込んで遊ぶためのぬいぐるみです。',2,1000,'/img/kerigurumi.jpg','2026/02/27','まどろみペット'),
('ケージ','けーじ','小鳥の室内飼い向けのケージです。',3,4000,'/img/bird_cage.jpg','2026/02/27','WHEY');
