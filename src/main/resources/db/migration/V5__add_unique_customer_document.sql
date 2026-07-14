ALTER TABLE customers
    ADD CONSTRAINT uk_customer_document
        UNIQUE (document_type, document_number);