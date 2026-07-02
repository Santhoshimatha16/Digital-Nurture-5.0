DECLARE
    CURSOR cust_cursor IS
        SELECT CustomerID, Balance
        FROM Customers;

BEGIN
    FOR cust IN cust_cursor LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer '|| cust.CustomerID || ' promoted to VIP');
        END IF;
    END LOOP;

    COMMIT;
END;
/