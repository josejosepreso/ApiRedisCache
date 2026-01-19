CREATE SCHEMA sch_catalog;
CREATE SCHEMA sch_users;

CREATE TABLE sch_catalog.products (
	product_id INT IDENTITY(1,1) PRIMARY KEY,
	brand_name NVARCHAR(255) NOT NULL,
	manufacturer NVARCHAR(255) NOT NULL,
	price_inr DECIMAL(10,2) NOT NULL,
	is_discontinued BIT NOT NULL,
	dosage_form NVARCHAR(50),
	pack_size DECIMAL(10,2),
	pack_unit NVARCHAR(50),
	num_active_ingredients INT,
	primary_ingredient NVARCHAR(255),
	primary_strength NVARCHAR(50),
	active_ingredients NVARCHAR(MAX),
	therapeutic_class NVARCHAR(100),
	packaging_raw NVARCHAR(255),
	manufacturer_raw NVARCHAR(255)
);

CREATE TABLE sch_users.users (
	id INT IDENTITY(1,1) PRIMARY KEY,
	email NVARCHAR(255) NOT NULL UNIQUE,
	first_name NVARCHAR(100) NOT NULL,
	last_name NVARCHAR(100) NOT NULL,
	active BIT NOT NULL,
	admin BIT NOT NULL
);

CREATE OR ALTER PROCEDURE sch_users.USER_INSERT
	@email NVARCHAR(255),
	@first_name NVARCHAR(100),
	@last_name NVARCHAR(100),
	@active BIT
AS
BEGIN
	SET NOCOUNT ON;
	
	INSERT INTO sch_users.users (email, first_name, last_name, active, admin)
	VALUES (@email, @first_name, @last_name, @active, 0);
	
	SELECT SCOPE_IDENTITY() AS user_id;
END;
