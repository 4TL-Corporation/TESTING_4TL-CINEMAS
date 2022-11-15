CREATE DATABASE ASM_JAVA4;
go

use ASM_JAVA4
go 

--Table Users
Create table Users(
	Username varchar(30) NOT NULL,
	Password nvarchar(50) NOT NULL,
	Fullname nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	Admin bit ,
	primary key (Username),
)
GO

--Table Videos
Create table Videos(
	VideoID varchar(200) NOT NULL,
	Title nvarchar(100) NOT NULL,
	Poster nvarchar(150) NOT NULL,
	Views int NOT NULL,
	Description nvarchar(1500),
	Active bit NOT NULL,
	primary key (VideoID),
)
GO

--Table Favorites
Create table Favorites(
	FavoriteID int IDENTITY(1,1) NOT NULL,
	Username varchar(30) NOT NULL,
	VideoID varchar(200) NOT NULL,
	LikeDate date,
	isLiked bit,
	primary key (FavoriteID),
	foreign key(Username) references Users(Username),
	foreign key(VideoID) references Videos(VideoID),
)
GO

--Table Shares
Create table Shares(
	ShareID int IDENTITY(1,1) NOT NULL,
	Username varchar(30) ,
	VideoID varchar(200) ,
	Emails nvarchar(250) ,
	ShareDate date ,
	primary key (ShareID),
	foreign key(Username) references Users(Username),
	foreign key(VideoID) references Videos(VideoID),
)
GO

-- Add data:
-- Table Users 
insert into Users values 
	('admin','admin123','Administrator','admin@123',1),
	('user1','asd123','Khanh','khanh@123',0),
	('user2','asd123','Han','han@123',0),
	('user3','asd123','Huy','huy@123',0),
	('user4','asd123','Duong','Duong@123',0),
	('khanh','asd','Khanh','Khanh@123',0)
GO

--Table Videos
insert into Videos values 
	('https://www.youtube.com/embed/gbug3zTm3Ws','Avenger','mv-it1.jpg',5,null,1),
	('https://www.youtube.com/embed/44LdLqgOpjo','John Wick','mv-it2.jpg',5,null,1),
	('https://www.youtube.com/embed/w0qQkSuWOS8','Witcher','mv-it3.jpg',5,null,1),
	('https://www.youtube.com/embed/1Q8fG0TtVAY','Love Thunder 1','mv-it4.jpg',5,null,1),
	('https://www.youtube.com/embed/_Z3QKkl1WyM','Love Thunder 2','mv-it5.jpg',5,null,1),
	('https://www.youtube.com/embed/e3Nl_TCQXuw','Iron Man 1','mv-it6.jpg',5,null,1)

GO

--Table Favorites
insert into Favorites values 
	('user1','https://www.youtube.com/embed/gbug3zTm3Ws','2021-12-14',1),
	('user2','https://www.youtube.com/embed/44LdLqgOpjo','2021-02-14',1),
	('user3','https://www.youtube.com/embed/w0qQkSuWOS8','2021-04-14',0),
	('user4','https://www.youtube.com/embed/_Z3QKkl1WyM','2021-12-14',0),
	('user4','https://www.youtube.com/embed/e3Nl_TCQXuw','2021-12-14',1),
	('user3','https://www.youtube.com/embed/gbug3zTm3Ws','2021-12-14',0),
	('user2','https://www.youtube.com/embed/44LdLqgOpjo','2021-02-14',1),
	('user1','https://www.youtube.com/embed/w0qQkSuWOS8','2021-04-14',1),
	('user2','https://www.youtube.com/embed/_Z3QKkl1WyM','2021-12-14',0),
	('user3','https://www.youtube.com/embed/e3Nl_TCQXuw','2021-12-14',1),
	('user4','https://www.youtube.com/embed/w0qQkSuWOS8','2021-04-14',1)
GO

--Table Shares
insert into Shares values 
	('user1','https://www.youtube.com/embed/gbug3zTm3Ws','khanh@123','2021-12-14'),
	('user2','https://www.youtube.com/embed/44LdLqgOpjo','khanh@123','2021-12-14'),
	('user3','https://www.youtube.com/embed/w0qQkSuWOS8','khanh@123','2021-12-14'),
	('user4','https://www.youtube.com/embed/_Z3QKkl1WyM','khanh@123','2021-12-14'),
	('user4','https://www.youtube.com/embed/e3Nl_TCQXuw','khanh@123','2021-12-14')
GO	
