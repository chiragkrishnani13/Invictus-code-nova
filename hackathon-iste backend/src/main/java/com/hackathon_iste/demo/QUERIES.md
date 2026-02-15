Create DATABASE IF NOT EXISTS hackathonps1;
USE hackathonps1;

CREATE TABLE users (
user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
email VARCHAR(150) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
city VARCHAR(100),
country VARCHAR(100),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
AlTER TABLE users
Add active_yn TINYINT DEFAULT 1;
Alter table users
Add phone varchar(20);
Alter table users
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP


CREATE TABLE space_events (
event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
event_name VARCHAR(150) NOT NULL,
event_type VARCHAR(50),
start_time DATETIME,
end_time DATETIME,
description TEXT,
replay_available BOOLEAN DEFAULT FALSE
);
CREATE TABLE city_event_visibility (
visibility_id BIGINT AUTO_INCREMENT PRIMARY KEY,
event_id BIGINT,
city VARCHAR(100),
country VARCHAR(100),
latitude DECIMAL(9,6),
longitude DECIMAL(9,6),
visible_from DATETIME,
visible_to DATETIME,
FOREIGN KEY (event_id) REFERENCES space_events(event_id)
);
CREATE TABLE event_replays (
replay_id BIGINT AUTO_INCREMENT PRIMARY KEY,
event_id BIGINT,
replay_url_img TEXT,
description Varchar(200),
duration_seconds INT,
FOREIGN KEY (event_id) REFERENCES space_events(event_id)
);
CREATE TABLE educational_content (
content_id BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(150),
category VARCHAR(50),
explanation TEXT,
real_world_use TEXT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE quiz_questions (
question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
content_id BIGINT,
question TEXT,
option_a VARCHAR(255),
option_b VARCHAR(255),
option_c VARCHAR(255),
correct_option CHAR(1),
FOREIGN KEY (content_id) REFERENCES educational_content(content_id)
);
CREATE TABLE user_quiz_attempts (
attempt_id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_id BIGINT,
question_id BIGINT,
selected_option CHAR(1),
is_correct BOOLEAN,
attempted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (question_id) REFERENCES quiz_questions(question_id)
);
CREATE TABLE observations (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT NOT NULL,
event_type ENUM('SATELLITE', 'METEOR', 'AURORA', 'PLANET', 'OTHER') NOT NULL,
title VARCHAR(200) NOT NULL,
description TEXT,
latitude DECIMAL(10, 8) NOT NULL,
longitude DECIMAL(11, 8) NOT NULL,
location_name VARCHAR(255),
observed_at TIMESTAMP NOT NULL,
photo_url VARCHAR(500),
upvotes INT DEFAULT 0,
downvotes INT DEFAULT 0,
view_count INT DEFAULT 0,
verification_status ENUM('PENDING', 'VERIFIED', 'DISPUTED', 'REJECTED') DEFAULT 'PENDING',
verification_method VARCHAR(50),
verification_details TEXT,
satellite_name VARCHAR(100),
satellite_norad_id INT,
is_deleted BOOLEAN DEFAULT FALSE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user (user_id),
    INDEX idx_event_type (event_type),
    INDEX idx_location (latitude, longitude),
    INDEX idx_observed_at (observed_at),
    INDEX idx_verification (verification_status),
    INDEX idx_created_at (created_at)
);

CREATE TABLE observation_votes (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
observation_id BIGINT NOT NULL,
user_id BIGINT NOT NULL,
vote_type ENUM('UPVOTE', 'DOWNVOTE') NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (observation_id) REFERENCES observations(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    UNIQUE KEY unique_vote (observation_id, user_id),
    INDEX idx_observation (observation_id),
    INDEX idx_user (user_id)
);



