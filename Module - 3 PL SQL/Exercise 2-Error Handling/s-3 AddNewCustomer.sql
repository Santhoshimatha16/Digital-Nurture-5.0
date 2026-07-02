CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_balance     IN NUMBER
)
IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM Customers
    WHERE CustomerID = p_customer_id;

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20003,
        'Customer ID already exists');
    END IF;

    INSERT INTO Customers
    (CustomerID, CustomerName, Balance)
    VALUES
    (p_customer_id, p_name, p_balance);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer added successfully');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/