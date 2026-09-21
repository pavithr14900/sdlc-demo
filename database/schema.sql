CREATE TABLE Employee (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE
);

CREATE TABLE Expense (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    employee_id UUID NOT NULL REFERENCES Employee(id),
    amount DECIMAL NOT NULL,
    description VARCHAR NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE Receipt (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    expense_id UUID NOT NULL REFERENCES Expense(id),
    file_path VARCHAR NOT NULL
);

CREATE TABLE Approval (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    expense_id UUID NOT NULL REFERENCES Expense(id),
    manager_id UUID NOT NULL REFERENCES Employee(id),
    status VARCHAR NOT NULL CHECK (status IN ('approved','rejected')),
    comment VARCHAR,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Policy (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    description VARCHAR,
    threshold DECIMAL NOT NULL
);

CREATE TABLE Report (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data JSONB NOT NULL
);

CREATE TABLE Audit (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    entity_id UUID NOT NULL,
    entity_type VARCHAR NOT NULL,
    action VARCHAR NOT NULL CHECK (action IN ('create', 'update', 'delete')),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id UUID NOT NULL REFERENCES Employee(id),
    changes JSONB
);
