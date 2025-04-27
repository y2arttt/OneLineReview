
USE OneLineReview;

-- 1. users
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       nickname VARCHAR(255) UNIQUE,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP NOT NULL
);

-- 2. books
CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       isbn VARCHAR(255) COMMENT
                       title VARCHAR(255),
                       image VARCHAR(500),
                       author VARCHAR(255),
                       discount VARCHAR(100),
                       publisher VARCHAR(255),
                       pubdate VARCHAR(50),
                       description VARCHAR(1000),
                       link VARCHAR(500)
);

-- 3. review
CREATE TABLE review (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        body TEXT,
                        user_id INT NOT NULL,
                        rating DOUBLE,
                        created_at TIMESTAMP NOT NULL,
                        recent_at TIMESTAMP NOT NULL,
                        book_id INT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (book_id) REFERENCES books(id)
);

-- 4. recommend_review
CREATE TABLE recommend_review (
                                  user_id INT NOT NULL,
                                  review_id INT NOT NULL,
                                  recommend INT NOT NULL COMMENT '추천=1 비추=-1',
                                  PRIMARY KEY (user_id, review_id),
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (review_id) REFERENCES review(id)
);

-- 5. comment
CREATE TABLE comment (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         review_id INT NOT NULL,
                         user_id INT NOT NULL,
                         content VARCHAR(1000) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         recent_at TIMESTAMP NOT NULL,
                         is_delete INT NOT NULL,
                         FOREIGN KEY (review_id) REFERENCES review(id),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 6. readingbook
CREATE TABLE readingbook (
                             book_id INT NOT NULL,
                             user_id INT NOT NULL,
                             start_at DATE,
                             end_at DATE,
                             reading BOOLEAN,
                             PRIMARY KEY (book_id, user_id),
                             FOREIGN KEY (book_id) REFERENCES books(id),
                             FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE VIEW review_with_image AS
SELECT r.id, r.body, r.rating, r.created_at, r.recent_at, r.user_id, r.book_id,
       b.image, b.title, b.isbn
FROM review r
         JOIN books b ON r.book_id = b.id;