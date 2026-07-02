DECLARE
    CURSOR cust_cursor IS
        SELECT CustomerID, Age
        FROM Customers;

BEGIN
    FOR cust IN cust_cursor LOOP
        IF cust.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Discount applied for Customer ID: '|| cust.CustomerID);
        END IF;
    END LOOP;

    COMMIT;
END;
/