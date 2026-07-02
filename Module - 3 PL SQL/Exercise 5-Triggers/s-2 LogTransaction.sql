CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        AuditID,
        TransactionID,
        ActionDate,
        ActionType
    )
    VALUES (
        AuditLog_Seq.NEXTVAL,
        :NEW.TransactionID,
        SYSDATE,
        'INSERT'
    );
END;
/