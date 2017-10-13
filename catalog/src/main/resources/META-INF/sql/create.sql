-- clean
DROP TABLE IF EXISTS item_attribute;
DROP TABLE IF EXISTS item;
DROP SEQUENCE IF EXISTS item_seq;

DROP TABLE IF EXISTS attribute;
DROP SEQUENCE IF EXISTS attribute_seq;

-- item
CREATE SEQUENCE item_seq;
GRANT ALL PRIVILEGES ON SEQUENCE item_seq TO admin;

CREATE TABLE item (
  id BIGINT PRIMARY KEY DEFAULT nextval('item_seq'),
  model VARCHAR(64),
  sku VARCHAR(64) NOT NULL,
  price DECIMAL(15,4) NOT NULL DEFAULT '0.0000'
);

GRANT ALL PRIVILEGES ON TABLE item TO admin;

-- attribute
CREATE SEQUENCE attribute_seq;
GRANT ALL PRIVILEGES ON SEQUENCE attribute_seq TO admin;

CREATE TABLE attribute (
  id BIGINT PRIMARY KEY DEFAULT nextval('attribute_seq'),
  description VARCHAR(64) NOT NULL,
  sort_order INT NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE attribute TO admin;

-- item attribute
CREATE TABLE item_attribute (
  item_id INT REFERENCES item (id) ON UPDATE CASCADE ON DELETE CASCADE,
  attribute_id INT REFERENCES attribute (id) ON UPDATE CASCADE,
  value VARCHAR(64)
);
GRANT ALL PRIVILEGES ON TABLE item_attribute TO admin;