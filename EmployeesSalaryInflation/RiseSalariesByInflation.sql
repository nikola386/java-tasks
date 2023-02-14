CREATE PROCEDURE dbo.RiseSalariesByInflation
(@InflationRate decimal(5,4),
@UpdatedRecords int output,
@Error varchar(max) output)
AS
BEGIN
SET NOCOUNT OFF

IF (ISNULL(@InflationRate, 0) = 0 OR @InflationRate > 1)
BEGIN
    RAISERROR('Invalid parameter: @InflationRate cannot be NULL, zero or larger than 1', 18, 0)
    RETURN
END

DECLARE
	@current_salary smallmoney,
	@new_salary smallmoney,
	@rows_updated int = 0;

DECLARE db_cursor CURSOR FOR
SELECT Salary
FROM dbo.Employee

OPEN db_cursor
FETCH NEXT FROM db_cursor INTO @current_salary

WHILE @@FETCH_STATUS = 0
BEGIN
	  SET @rows_updated = @rows_updated + 1;
      SET @new_salary = @current_salary + @current_salary * @InflationRate
      UPDATE dbo.Employee
      SET Salary = @new_salary,
      	LastUpdated = GETDATE()
      WHERE CURRENT OF db_cursor;

      FETCH NEXT FROM db_cursor INTO @current_salary
END

CLOSE db_cursor
DEALLOCATE db_cursor

SELECT @UpdatedRecords = @rows_updated, @Error = @@ERROR

END