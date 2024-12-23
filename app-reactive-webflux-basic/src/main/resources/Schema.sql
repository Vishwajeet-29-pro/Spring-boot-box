CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,         -- Auto-incrementing ID
    title VARCHAR(255) NOT NULL,      -- Task title, cannot be null
    description TEXT,                 -- Task description
    status VARCHAR(50) NOT NULL,      -- Enum status, stored as a string
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Auto-set when record is created
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  -- Auto-set when record is updated
);
