# subscription

I have used h2 database and these are the queries that I have used:

CREATE TABLE subscription_master (
    Id NVARCHAR(255) PRIMARY KEY,
    title NVARCHAR(255),
    type NVARCHAR(255),
    price NVARCHAR(255)
);

INSERT INTO subscription_master (Id, title, type, price) VALUES ('1', 'Basic', 'Monthly', '99');
INSERT INTO subscription_master (Id, title, type, price) VALUES ('2', 'Premium', 'Monthly', '199');
INSERT INTO subscription_master (Id, title, type, price) VALUES ('3', 'Basic', 'Yearly', '999');
INSERT INTO subscription_master (Id, title, type, price) VALUES ('4', 'Premium', 'Yearly', '1999');


CREATE TABLE Users_Data (
    id NVARCHAR(255) PRIMARY KEY,
    fullName NVARCHAR(255),
    email NVARCHAR(255),
    mobile NVARCHAR(255),
    password NVARCHAR(255),
    dateCreated DATETIME,
    dateUpdated DATETIME
);


INSERT INTO Users_Data (id, fullName, email, mobile, password, dateCreated, dateUpdated) VALUES 
('1', 'John Doe', 'john.doe@example.com', '1234567890', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Users_Data (id, fullName, email, mobile, password, dateCreated, dateUpdated) VALUES 
('2', 'Jane Smith', 'jane.smith@example.com', '0987654321', 'securepassword', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


CREATE TABLE user_subscription (
    id NVARCHAR(255) PRIMARY KEY,
    userId NVARCHAR(255),
    subscription_master_id NVARCHAR(255),
    start_date DATETIME,
    end_date DATETIME
);


INSERT INTO user_subscription (id, userId, subscription_master_id, start_date, end_date) VALUES 
('1', '1', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); //John Doe Premium Monthly 199

INSERT INTO user_subscription (id, userId, subscription_master_id, start_date, end_date) VALUES 
('2', '1', '3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); //John Doe Premium Monthly 199
