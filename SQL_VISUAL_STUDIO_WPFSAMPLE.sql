--CREATE DATABASE WPFSample
--GO 
--USE WPFSample
--GO
--DROP TABLE Personas
CREATE TABLE Personas (
	IDPersona int NOT NULL IDENTITY PRIMARY KEY,
	Nombre varchar(20) NOT NULL,
	Apellidos varchar(20) NOT NULL,
	Fecha_Nacimiento date NOT NULL,
	Direccion varchar(200) NULL,
	Telefono varchar(13) NULL,
	CodDepartamento int NOT NULL,
	CONSTRAINT FK_PERSONAS_DEPARTAMENTO FOREIGN KEY (CodDepartamento) REFERENCES Departamentos(IDDepartamento)
)

--GO
--USE WPFSample
--GO
--SELECT * FROM Personas

--USE [WPFSample]
--GO

--INSERT INTO [dbo].[Personas]([Nombre],[Apellidos],[Fecha_Nacimiento],[Direccion],[Telefono],[CodDepartamento])VALUES('David','Castillo','20150505','Calle Random','999888777',1)
--GO
--DROP TABLE Departamentos
CREATE TABLE Departamentos (
	IDDepartamento int IDENTITY PRIMARY KEY,
	Nombre varchar(20) NOT NULL
)
GO
USE [WPFSample]
GO

INSERT INTO [dbo].[Departamentos]([Nombre])VALUES('Ventas')
GO

CREATE TABLE Usuarios (
	IDUsuario int IDENTITY PRIMARY KEY,
	Nombre varchar(20) NOT NULL,
	Pass varchar(32) NOT NULL
)
GO
DECLARE @Nombre varchar(20) = 'david'
SELECT * FROM Usuarios WHERE Nombre = @Nombre
GO
-- Procedimiento almacenado para insertar una persona en la base de datos, se le asignará el departamento por defecto. 
-- Devuelve el id de la persona insertada.
CREATE PROCEDURE sp_insertarPersona(@Nombre varchar(20), @Apellidos varchar(20), @Fecha_Nacimiento date, @Direccion varchar(200) = null, @Telefono varchar(13) = null, @idInsertado int OUTPUT) AS
	BEGIN

	DECLARE @COD_DEPARTAMENTO int = 4 --Código para asociar la nueva persona con el departamento por defecto.

	INSERT INTO dbo.Personas(Nombre,Apellidos,Fecha_Nacimiento,Direccion,Telefono,CodDepartamento) 
		VALUES (@Nombre,@Apellidos,@Fecha_Nacimiento,@Direccion,@Telefono,@COD_DEPARTAMENTO)

	SET @idInsertado = (SELECT IDPersona FROM Personas WHERE Nombre = @Nombre)

	END
GO
SELECT * FROM PERSONAS
DELETE FROM PERSONAS WHERE IDPersona = 9