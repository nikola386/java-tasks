CREATE PROCEDURE dbo.RiseSalaries
(@Department varchar(100),
@Promotion smallmoney,
@UpdatedRecords int output,
@Error varchar(max) output)
AS
BEGIN
SET NOCOUNT OFF

IF (ISNULL(@Department, '') = '')
BEGIN
    RAISERROR('Invalid parameter: @Department cannot be NULL or empty', 18, 0)
    RETURN
END

IF (ISNULL(@Promotion, 0) = 0)
BEGIN
    RAISERROR('Invalid parameter: @Promotion cannot be NULL or zero', 18, 0)
    RETURN
END

UPDATE dbo.Employee
   SET Salary = Salary + @Promotion
 WHERE Department = @Department

SELECT @UpdatedRecords = @@ROWCOUNT, @Error = @@ERROR

END