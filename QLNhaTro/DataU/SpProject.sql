-----Tạo thủ tục-----
---1.USER
--chọn all user
create proc sp_selectAllUsers
as
begin
select * from Users
end;

exec sp_selectAllUsers

--Select login
create proc sp_Login
(@username varchar(50),
@pass varchar(50)
)
as
begin
select * from Users where username = @username and pass = @pass
end

--chọn User
create proc sp_selectUser
(@username varchar(50))
as
begin
select * from Users where username = @username
end

exec sp_selectUser 'longnn'

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

exec sp_deleteUser 6
exec sp_deleteUser 7

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

exec sp_insertNV 'NV','',N'Nguyễn Ngọc Long','Nam','0175867299','3985456436','LongNN@gmail.com','KA'

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
update NhanVien set trangthai= '' where userid = 4
end

exec sp_deleteNV 'abc'

--Tìm NV
drop proc sp_findNVByMANV
create proc sp_findNVByMANV
(@manv varchar(10))
as
begin
select * from NhanVien where manv = @manv and trangthai != 'deleted'
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

delete from KhachTro where makt = 'abc'

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
drop proc sp_findKTByMAKT
create proc sp_findKTByMAKT
(@makt varchar(10))
as
begin
select * from KhachTro where makt = @makt and trangthai != 'deleted'
end

exec sp_findKTByMAKT 'ND001'

---5. Dịch Vụ
--Chọn tất cả DV
create proc sp_selectAllDV
as
begin
select * from DichVu
end

exec sp_selectAllDV

--Thêm KT
create proc sp_insertDV
(
@madv varchar(10),
@tendv nvarchar(100),
@gia float
)
as 
begin
insert into DichVu values(@madv, @tendv, @gia)
end

exec sp_insertDV 'abc', 'abc', 10000

--Sửa DV
create proc sp_updateDV
(
@madv varchar(10),
@gia float
)
as 
begin
update DichVu set gia = @gia where madv = @madv
end

exec sp_updateDV 'abc', 20000

--Xóa DV
create proc sp_deleteDV
(
@madv varchar(10)
)
as 
begin
delete from DichVu where madv = @madv
end

exec sp_deleteDV 'abc'

---5.Hợp đồng
-- Chọn All HD
create proc sp_selectAllHD
as
begin
select * from HopDong where trangthai !='deleted'
end

exec sp_selectAllHD

-- thêm hợp đồng
drop proc sp_insertHD


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
@trangthai varchar(10),
@nguoitao varchar(10) ,
@madv varchar(10),
@ngaythanhtoandv date
 )
as
begin
insert into HopDong values(@nguoidaidien , @songuoi , @nguoio, @mahddv, @maphong ,@ngaythue , @ngayhethan ,@thanhtien, @trangthai, @nguoitao, @madv,@ngaythanhtoandv )
update Phong set trangthai = N'Đã thuê' where maphong = @maphong
end

insert into HoaDonDV values('DV1,DV2,DV3','P1','100000','','longnn')
exec sp_insertHD 'ND001',3,'ND005,ND003,ND004',1,'P1','','','','','longnn','DV1',''

--Xóa hợp đồng
drop proc sp_deleteHD
create proc sp_deleteHD
(
@mahd int
)
as
begin
update HopDong set trangthai = 'deleted' where mahd= @mahd
update Phong set trangthai = N'Trống' where maphong= (select maphong from HopDong where mahd= @mahd)
update KhachTro set trangthai = N'deleted' where maphong = (select maphong from HopDong where mahd= @mahd)
end

select * from KhachTro

delete KhachTro where maphong = 'P3'

exec sp_deleteHD '8'

select * from NhanVien

--Update Hợp đồng
drop proc sp_updateHD
create proc sp_updateHD
(
@mahd int,
@songuoi int ,
@nguoio nvarchar(200),
@ngayhethan date,
@thanhtien float,
@trangthai varchar(10)
)
as
begin
update HopDong set songuoi = @songuoi, nguoio = @nguoio, ngayhethan = @ngayhethan, thanhtien = @thanhtien, trangthai = @trangthai where mahd=@mahd
end

exec sp_updateHD 1, 3, N'ND005,ND003,ND004', '2019-11-28'

--Tìm Hợp đồng
drop proc sp_findHDByMaPhong
create proc sp_findHDByMaPhong
(
@maphong varchar(10)
)
as
begin
select * from HopDong where maphong = @maphong
end

exec sp_findHDByMaPhong'P1'


---6. HÓA ĐƠN DỊCH VỤ
--Chọn all maHDDV
create proc sp_selectAllHDDV
as
begin
select * from HoaDonDV where trangthai !='deleted'
end

exec sp_selectAllHDDV

--Thêm Hóa Đơn DV
create proc sp_insertHDDV
(
@sudung nvarchar(255), 
@maphong varchar(10),
@tongtien float,
@trangthai nvarchar(50) ,
@nguoitao varchar(10)
)
as
begin
insert into HoaDonDV values(@sudung,@maphong,@tongtien,@trangthai,@nguoitao)
end

exec sp_insertHDDV ?, ?, ?, ?, ?

--update HDDV
drop proc sp_insertHDDV
create proc sp_updateHDDV
(
@sudung nvarchar(255), 
@maphong varchar(10),
@tongtien float,
@trangthai nvarchar(50)
)
as
begin
update HoaDonDV set sudung = @sudung, tongtien= @tongtien, @trangthai = @trangthai where maphong = @maphong
end

exec sp_updateHDDV ?, ?, ?, ?, ?

--Tìm theo mã phòng 
create proc sp_selectMaHDDVByMaPhong(
@maphong varchar(10)
)
as
begin
select * from HoaDonDV where maphong = @maphong
end

exec sp_selectMaHDDVByMaPhong 'P1'


select * from DichVu

---7. HÓA ĐƠN CHI TIẾT
--Chọn all HDCT
create proc sp_selectAllHDCT
as
begin
select * from HoaDonChiTiet
end

exec sp_selectAllHDCT
select 

---Thêm HDCT
drop proc sp_insertHDCT
create proc sp_insertHDCT
(
@dichvu varchar(10),
@maphong varchar(10),
@thanhtien float,
@thoigian nvarchar(50),
@mahddv int
)
as
begin
insert into HoaDonChiTiet values(@dichvu,@maphong,@thanhtien,@thoigian,@mahddv)
end

exec sp_insertHDCT ?,?,?,?,?

--Tìm HDCT theo mã phòng
create proc sp_selectMaHDCTByMaPhong(
@maphong varchar(10)
)
as
begin
select * from HoaDonChiTiet where maphong = @maphong
end

exec sp_selectMaHDCTByMaPhong 'P1'

---Thống kê doanh thu---
drop proc sp_ThongKeDoanhThu
create proc sp_ThongKeDoanhThu
as
begin
SELECT DichVu.madv, DichVu.tendv, SUM(HoaDonChiTiet.thanhtien) as DoanhThu
FROM DichVu  inner join HoaDonChiTiet on HoaDonChiTiet.dichvu = DichVu.madv
GROUP BY madv, tendv
end

exec sp_ThongKeDoanhThu
---Thống kê doanh thu năm và tháng---
create proc sp_ThongKeDoanhThuTheoThang(
@thang int,
@nam int
)
as
begin
SELECT DichVu.madv, DichVu.tendv, SUM(HoaDonChiTiet.thanhtien) as DoanhThu
FROM DichVu  inner join HoaDonChiTiet on HoaDonChiTiet.dichvu = DichVu.madv
WHERE Month(HoaDonChiTiet.thoigian) = @thang and YEAR(HoaDonChiTiet.thoigian) = @nam
GROUP BY madv, tendv
end

exec sp_ThongKeDoanhThuTheoThang 12, 2018
---Sắp xếp ThongKe Tang
drop proc sp_SapXepThongKeTang

create proc sp_SapXepThongKeTang
(
@thang int,
@nam int
)
as
begin
SELECT DichVu.madv, DichVu.tendv, SUM(HoaDonChiTiet.thanhtien) as DoanhThu
FROM DichVu  inner join HoaDonChiTiet on HoaDonChiTiet.dichvu = DichVu.madv
WHERE Month(HoaDonChiTiet.thoigian) = @thang and YEAR(HoaDonChiTiet.thoigian) = @nam
GROUP BY madv, tendv
order by DoanhThu asc
end

exec sp_SapXepThongKeTang

---Sắp xếp ThongKe Giam
drop proc sp_SapXepThongKeGiam

create proc sp_SapXepThongKeGiam
(
@thang int,
@nam int
)
as
begin
SELECT DichVu.madv, DichVu.tendv, SUM(HoaDonChiTiet.thanhtien) as DoanhThu
FROM DichVu  inner join HoaDonChiTiet on HoaDonChiTiet.dichvu = DichVu.madv
WHERE Month(HoaDonChiTiet.thoigian) = @thang and YEAR(HoaDonChiTiet.thoigian) = @nam
GROUP BY madv, tendv
order by DoanhThu desc
end

exec sp_SapXepThongKeGiam
















