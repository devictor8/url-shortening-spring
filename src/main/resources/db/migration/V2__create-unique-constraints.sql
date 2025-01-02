ALTER TABLE url
ADD CONSTRAINT unique_url UNIQUE (url),
ADD CONSTRAINT unique_short_code UNIQUE (short_code)