-- 테이블 생성
CREATE TABLE calendar(
                         id             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         title          VARCHAR(100)      NOT NULL,
                         todoist        VARCHAR(200)      NOT NULL,
                         createdAt      DATETIME          NOT NULL DEFAULT now(),
                         updatedAt      DATETIME          NOT NULL DEFAULT now() ON UPDATE now(),
                         writer         VARCHAR(100)      NOT NULL,
                         password       VARCHAR(40)       NOT NULL
);

-- 일정 등록
INSERT INTO calendar(id, title, todoist,writer,password)
VALUES (id, title, todoist, writer, password);

-- 일정 목록 조회
SELECT id, title, todoist, createdAt, updatedAt, writer
FROM calendar
ORDER BY updatedAt DESC;

-- 일정 목록 작성자 조회
SELECT id, title, todoist, createdAt, updatedAt, writer
FROM calendar
WHERE writer = ?
ORDER BY updatedAt DESC;

-- 일정 목록 작성 날짜 조회
SELECT id, title, todoist, createdAt, updatedAt, writer
FROM calendar
WHERE updatedAt = ?
ORDER BY updatedAt DESC;

-- 일정 목록 작성자 또는 작성 날짜 조회
SELECT id, title, todoist, createdAt, updatedAt, writer
FROM calendar
WHERE writer = ? OR updatedAt = ?
ORDER BY updatedAt DESC;

-- 일정 조회
SELECT *
FROM calendar
WHERE id = ?;

-- 일정 수정
UPDATE calendar
SET todoist = ?, writer = ?, updatedAt = NOW()
WHERE id = ?;

-- 일정 삭제
DELETE
FROM calendar
WHERE id = ?;

-- ID 검증
SELECT *
FROM calendar
WHERE id = ?