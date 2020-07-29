use master
go

create database QLNhaTro
go
use QLNhaTro
go
create table Users(
userid int not null identity primary key,
username varchar(50) not null,
pass varchar(50) not null,
roles varchar(10) not null
)

create table KhuNha(
makhu varchar(10) not null primary key,
tenkhu nvarchar(100) not null
)

create table DichVu(
madv varchar(10) not null primary key,
tendv nvarchar(100) not null,
gia float not null default 1
)

create table Phong(
maphong varchar(10) not null primary key,
tenphong nvarchar(100) not null,
gia float not null,
dientich float not null,
trangthai nvarchar(100) not null,
makhu varchar(10) not null,
ngaythanhtoandv date not null default getdate(),
FOREIGN KEY (makhu) REFERENCES KhuNha(makhu)
)

create table KhachTro(
makt varchar(10) not null primary key,
tenkt nvarchar(100) not null,
gioitinh nvarchar(5) not null,
sodt varchar(15) not null,
socmt varchar(15) not null,
email nvarchar(100) not null,
maphong varchar(10) not null,
trangthai nvarchar(100) null,
foreign key (maphong) references Phong(maphong)
)

create table NhanVien(
manv varchar(10) not null primary key,
userid int not null unique,
tennv nvarchar(100) not null,
gioitinh nvarchar(5) not null,
sodt varchar(15) not null,
socmt varchar(15) not null,
email nvarchar(100) not null,
makhu varchar(10) not null,
trangthai nvarchar(100) null,
foreign key (userid) REFERENCES Users(userid),
foreign key (makhu) REFERENCES KhuNha(makhu)
)

create table HoaDonDV(
mahddv int identity not null primary key,
sudung nvarchar(255) not null, 
maphong varchar(10) not null,
tongtien float not null,
trangthai nvarchar(50) null,
nguoitao varchar(10) not null,
foreign key (nguoitao) REFERENCES NhanVien(manv),
foreign key (maphong) REFERENCES Phong(maphong)
)

create table HoaDonChiTiet(
mahdct int identity not null primary key,
dichvu varchar(10) not null,
maphong varchar(10) not null,
thanhtien float not null,
thoigian nvarchar(50) not null,
mahddv int not null,
foreign key (dichvu) REFERENCES DichVu(madv),
foreign key (mahddv) REFERENCES HoaDonDV(mahddv),
foreign key (maphong) REFERENCES Phong(maphong)
)

create table HopDong(
mahd int identity not null primary key,
nguoidaidien varchar(10) not null,
songuoi int not null default 1,
nguoio nvarchar(200) not null,
mahddv int not null unique,
maphong varchar(10) not null,
ngaythue date not null default getdate(),
ngayhethan date not null default getdate(),
thanhtien float not null default 0,
trangthai varchar(10) null,
nguoitao varchar(10) not null,
madv varchar(10) not null,
ngaythanhtoandv date not null default getdate(),
foreign key (nguoidaidien) REFERENCES KhachTro(makt),
foreign key (nguoitao) REFERENCES NhanVien(manv),
foreign key (maphong) REFERENCES Phong(maphong),
foreign key (mahddv) REFERENCES HoaDonDV(mahddv),
foreign key (madv) REFERENCES DichVu(madv)
)
go

insert into Users values('LongNN','123','NV')
insert into Users values('HaiTN','123','NV')
insert into Users values('HuongPT','123','NV')
insert into Users values('KhuongTM','123','NV')
insert into Users values('LuongDD','123','CN')

insert into KhuNha values('KA','Khu A')
insert into KhuNha values('KB','Khu B')
insert into KhuNha values('KC','Khu C')
insert into KhuNha values('KD','Khu D')
insert into KhuNha values('KE','Khu E')

insert into DichVu values('DV1', N'Điện',3300)
insert into DichVu values('DV2', N'Nước',3000)
insert into DichVu values('DV3', N'Internet',160000)
insert into DichVu values('DV4', N'Gửi xe',30000)

insert into Phong values('P1','Phòng 1',1000000,90, N'Trống', 'KA','')
insert into Phong values('P2','Phòng 2',1000000,90, N'Đã thuê', 'KB','')
insert into Phong values('P3','Phòng 3',1000000,90, N'Trống', 'KC','')
insert into Phong values('P4','Phòng 4',1000000,90, N'Sửa', 'KD','')
insert into Phong values('P5','Phòng 5',1000000,90, N'Trống', 'KE','')

insert into KhachTro values('ND001', N'Nguyễn Minh Tuấn',N'Nam','0192939299','002020020002','tuannm@gmail.com','P1','')
insert into KhachTro values('ND002', N'Dương Tấn Minh',N'Nam','0192939278','00432002232','minhdt@gmail.com','P2','')
insert into KhachTro values('ND003', N'Nguyễn Văn A',N'Nam','0192656289','00202002978','anv@gmail.com','P3','')
insert into KhachTro values('ND004', N'Nguyễn Thị T',N'Nữ','019289536','00123002005','tnt@gmail.com','P3','')
insert into KhachTro values('ND005', N'Hoàng K H',N'Nữ','014359267','06758654506','hkh@gmail.com','P3','')

insert into NhanVien values('NV001','1',N'Nguyễn Ngọc Long','Nam','0175867299','3985456436','LongNN@gmail.com','KA','')
insert into NhanVien values('NV002','2',N'Trần Ngọc Hải','Nam','0175867299','23435466436','HaiTN@gmail.com','KB','')
insert into NhanVien values('NV003','3',N'Phạm Thu Hương','Nữ','01776878','3988756745','HuongTM@gmail.com','KC','')
insert into NhanVien values('NV004','4',N'Trần Minh Khương','Nam','069578299','5466556436','KhuongTM@gmail.com','KE','')

insert into HoaDonDV values('DV1,DV2,DV3','P1','100000','','NV001')
insert into HoaDonDV values('DV1,DV2','P1','100000','','NV002')
insert into HoaDonDV values('DV1,DV2,DV3','P2','100000','','NV001')
insert into HoaDonDV values('DV1,DV2,DV4','P3','100000','','NV003')

insert into HoaDonChiTiet values('DV1','P1',50000,N'Tháng 1',1)
insert into HoaDonChiTiet values('DV2','P1',30000,N'Tháng 1',1)
insert into HoaDonChiTiet values('DV3','P1',30000,N'Tháng 1',1)
insert into HoaDonChiTiet values('DV2','P2',30000,N'Tháng 1',2)

insert into HopDong values('ND005',3,'ND005,ND003,ND004',1,'P1','','','','','NV001','DV1','')
insert into HopDong values('ND001',1,'ND001',2,'P2','','','','','NV002','DV3','')
insert into HopDong values('ND002',1,'ND002',3,'P5','','','','','NV003','DV2','')


select * from Phong
select * from KhuNha
select * from NhanVien
select * from KhachTro
select * from HopDong
select * from HoaDonDV


--mã hoá
update Users set pass =
EncryptByPassPhrase('abc', convert(varchar(100),pass))

--giải mã
update Users set pass =
convert(varchar(10),convert(varchar(100),decryptbypassphrase('abc',pass) ))
select * from Users
select
userid, pass=
convert(varchar(10),convert(varchar(100),decryptbypassphrase('abc',pass) )) from Users

-----Tạo thủ tục-----
---1.USER
--chọn all user
create proc sp_selectAllUsers
as
begin
select * from Users
end;

exec sp_selectAllUsers

--giả mã pass user chọn
create proc sp_convertPasstoString
(@username varchar(50))
as
begin
update Users set pass = convert(varchar(10),convert(varchar(100),decryptbypassphrase('abc',pass) )) where username = @username
end

exec sp_convertPasstoString N'LongNN'

--mã hóa pass user chọn
create proc sp_convertStringToPass
(@username varchar(50))
as
begin
update Users set pass = EncryptByPassPhrase('abc', convert(varchar(100),pass)) where username = @username
end

exec sp_convertStringToPass N'LongNN'

--Thêm user
create proc sp_insertUser
(
@username varchar(50),
@pass varchar(50),
@roles varchar(10)
)
as 
begin
insert into Users values(@username, @pass,@roles)
end

exec sp_insertUser 'abc','123','abc'

--Xóa user
create proc sp_deleteUser
(
@userid int 
)
as 
begin
delete from Users where userid = @userid
end

exec sp_deleteUser 1

--Đổi Mật Khẩu
create proc sp_changePass
(
@username varchar(50),
@pass varchar(50)
)
as 
begin
update Users set pass = @pass where username = @username
end

exec sp_changePass 'abc', 456

---2.PHÒNG
create proc sp_selectAllRoom
as
begin
select * from Phong
end;

exec sp_selectAllRoom

--Thêm phòng
create proc sp_insertRoom
(
@maphong varchar(10),
@tenphong nvarchar(100),
@gia float,
@dientich float,
@trangthai nvarchar(100),
@makhu varchar(10),
@ngaythanhtoandv date
)
as 
begin
insert into Phong values(@maphong, @tenphong, @gia, @dientich, @trangthai, @makhu, @ngaythanhtoandv)
end

exec sp_insertRoom'abc','123',1,10,'abc','KA', ''

--Xóa phòng
create proc sp_deleteRoom
(
@maphong varchar(10)
)
as 
begin
delete from Phong where maphong = @maphong
end

exec sp_deleteRoom 'abc'

--Sửa phòng
create proc sp_updateRoom
(
@maphong varchar(10),
@gia float,
@trangthai nvarchar(100)
)
as 
begin
update Phong set gia = @gia, trangthai =@trangthai where maphong = @maphong
end

exec sp_updateRoom 'abc', 20, 'jhk'

--Tìm phòng
create proc sp_findNVBySTT
(@trangthai nvarchar(100))
as
begin
select * from Phong where trangthai = @trangthai
end

exec sp_findNVBySTT N'Trống'


---3.NHÂN VIÊN
--Chọn All NV
create proc sp_selectAllNV
as
begin
select * from NhanVien where trangthai != 'deleted'
end;

exec sp_selectAllNV

--Thêm NV
create proc sp_insertNV
(
@manv varchar(10),
@userid int,
@tennv nvarchar(100),
@gioitinh nvarchar(5),
@sodt varchar(15),
@socmt varchar(15),
@email nvarchar(100),
@makhu varchar(10),
@trangthai nvarchar(100)
)
as 
begin
insert into NhanVien values(@manv,@userid,@tennv,@gioitinh,@sodt,@socmt,@email,@makhu,@trangthai)
end

exec sp_insertNV 'NV','1',N'Nguyễn Ngọc Long','Nam','0175867299','3985456436','LongNN@gmail.com','KA'

--Sửa NV
create proc sp_updateNV
(
@manv varchar(10),
@sodt varchar(15),
@socmt varchar(15),
@email nvarchar(100),
@makhu varchar(10)
)
as 
begin
update NhanVien set sodt = @manv, socmt= @socmt ,email = @email, makhu = @makhu where manv= @manv
end

exec sp_updateNV 'abc', 0363000459, 001200009605,'long@gmail.com','KA'

--Xóa NV
create proc sp_deleteNV
(
@manv varchar(10)
)
as 
begin
update NhanVien set trangthai= 'deleted' where manv = @manv
end

exec sp_deleteNV 'abc'

--Tìm NV
create proc sp_findNVByMANV
(@manv varchar(10))
as
begin
select * from NhanVien where manv = @manv
end

exec sp_findNVByMANV 'NV001'

---4.KHÁCH TRỌ
--Chọn all KT
create proc sp_selectAllKT
as
begin
select * from KhachTro where trangthai != 'deleted'
end;

exec sp_selectAllKT

--Thêm KT
create proc sp_insertKT
(
@makt varchar(10),
@tenkt nvarchar(100),
@gioitinh nvarchar(5),
@sodt varchar(15),
@socmt varchar(15),
@email nvarchar(100),
@maphong varchar(10),
@trangthai nvarchar(100)
)
as 
begin
insert into KhachTro values(@makt,@tenkt,@gioitinh,@sodt,@socmt,@email,@maphong,@trangthai)
end

exec sp_insertKT 'NV',N'Nguyễn Ngọc Long','Nam','0175867299','3985456436','LongNN@gmail.com','P1'

--Sửa KT
create proc sp_updateKT
(
@makt varchar(10),
@sodt varchar(15),
@socmt varchar(15),
@email nvarchar(100)
)
as 
begin
update KhachTro set sodt = @sodt, socmt= @socmt,email = @email where makt= @makt
end

exec sp_updateKT 'NV', 0363000459, 001200009605, 'long@gmail.com'

--Xóa KT
create proc sp_deleteKT
(
@makt varchar(10)
)
as 
begin
update KhachTro set trangthai = 'deleted' where makt = @makt
end

exec sp_deleteKT 'ND005'

--Tìm KT
create proc sp_findKTByMAKT
(@makt varchar(10))
as
begin
select * from KhachTro where makt = @makt
end

exec sp_findKTByMAKT 'ND001'

-------------hợp đồng
-- select all hợp đồng
create proc sp_selectAllHD
as
begin
select * from HopDong where trangthai !='deleted'
end

-- thêm hợp đồng
 create proc sp_insertHD
 (
			@nguoidaidien varchar(10) ,
			@songuoi int ,
			@nguoio nvarchar(200) ,
			@mahddv int ,
			@maphong varchar(10) ,
			@ngaythue date ,
			@ngayhethan date ,
			@thanhtien float,
			@trangthai varchar(10) ,
			@nguoitao varchar(10) ,
			@madv varchar(10) 
 )
 as
 begin
 insert into HopDong values(@nguoidaidien , @songuoi , @nguoio, @mahddv, @maphong ,@ngaythue , @ngayhethan ,@thanhtien, @trangthai, @nguoitao, @madv)
 end

-- xoá hợp đồng
create proc sp_deleteHD
(
@mahd int
)
as
begin
update HopDong set trangthai = 'deleted' where mahd= @mahd
update Phong set trangthai = N'Trống' where maphong= (select maphong from HopDong where mahd= @mahd)
end









