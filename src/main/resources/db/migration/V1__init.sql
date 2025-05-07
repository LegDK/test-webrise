-- Create sequences
CREATE SEQUENCE users_id_seq;
CREATE SEQUENCE subscriptions_id_seq;
CREATE SEQUENCE user_subscriptions_id_seq;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq'),
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE subscriptions (
    id BIGINT PRIMARY KEY DEFAULT nextval('subscriptions_id_seq'),
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE user_subscriptions (
    id BIGINT PRIMARY KEY DEFAULT nextval('user_subscriptions_id_seq'),
    user_id BIGINT NOT NULL REFERENCES users(id),
    subscription_id BIGINT NOT NULL REFERENCES subscriptions(id),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    UNIQUE(user_id, subscription_id)
); 