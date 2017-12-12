CREATE TABLE abilities
(
  ability_id    INT AUTO_INCREMENT
    PRIMARY KEY,
  generation_id INT        NULL,
  language      VARCHAR(7) NULL,
  name          TEXT       NULL,
  description   TEXT       NULL
)
  ENGINE = InnoDB;

CREATE INDEX abilities_generation_id_index
  ON abilities (generation_id);

CREATE TABLE attackeffects
(
  effect_id   INT AUTO_INCREMENT
    PRIMARY KEY,
  game_id     INT  NULL,
  effect_type TEXT NULL
)
  ENGINE = InnoDB;

CREATE INDEX attackeffects_games_game_id_fk
  ON attackeffects (game_id);

CREATE TABLE attacknames
(
  attack_id     INT        NOT NULL,
  language      VARCHAR(7) NOT NULL,
  name          TEXT       NULL,
  description   TEXT       NULL,
  generation_id INT        NULL,
  PRIMARY KEY (attack_id, language)
)
  ENGINE = InnoDB;

CREATE INDEX attacknames_generations_generation_id_fk
  ON attacknames (generation_id);

CREATE TABLE attacks
(
  attack_id     INT AUTO_INCREMENT
    PRIMARY KEY,
  generation_id INT NULL,
  base_power    INT NULL,
  accuracy      INT NULL,
  mode          INT NULL,
  type          INT NULL
)
  ENGINE = InnoDB;

CREATE INDEX attacks_generation_id_index
  ON attacks (generation_id);

CREATE INDEX attacks_mode_index
  ON attacks (mode);

CREATE INDEX attacks_type_index
  ON attacks (type);

ALTER TABLE attacknames
  ADD CONSTRAINT attacknames_attacks_attack_id_fk
FOREIGN KEY (attack_id) REFERENCES attacks (attack_id);

CREATE TABLE attackswitheffects
(
  attack_id INT NULL,
  game_id   INT NULL,
  effect_id INT NULL,
  chance    INT NULL,
  CONSTRAINT attackswitheffects_attacks_attack_id_fk
  FOREIGN KEY (attack_id) REFERENCES attacks (attack_id),
  CONSTRAINT attackswitheffects_attackeffects_effect_id_fk
  FOREIGN KEY (effect_id) REFERENCES attackeffects (effect_id)
)
  ENGINE = InnoDB;

CREATE INDEX attackswitheffects_attacks_attack_id_fk
  ON attackswitheffects (attack_id);

CREATE INDEX attackswitheffects_games_game_id_fk
  ON attackswitheffects (game_id);

CREATE INDEX attackswitheffects_attackeffects_effect_id_fk
  ON attackswitheffects (effect_id);

CREATE TABLE catchrates
(
  pokemon_id INT NOT NULL,
  game_id    INT NOT NULL,
  catch_rate INT NULL,
  PRIMARY KEY (pokemon_id, game_id)
)
  ENGINE = InnoDB;

CREATE INDEX catchrates_games_game_id_fk
  ON catchrates (game_id);

CREATE TABLE egggroups
(
  pokemon_id INT NOT NULL,
  game_id    INT NOT NULL,
  egg_group  INT NOT NULL,
  PRIMARY KEY (pokemon_id, game_id, egg_group)
)
  ENGINE = InnoDB;

CREATE INDEX egggroups_games_game_id_fk
  ON egggroups (game_id);

CREATE TABLE evolutions
(
  game_id          INT                    NOT NULL,
  from_id          INT                    NOT NULL,
  to_id            INT                    NOT NULL,
  is_mega          TINYINT(1) DEFAULT '0' NULL,
  evolution_method TEXT                   NULL,
  evolution_point  INT                    NULL,
  PRIMARY KEY (game_id, from_id, to_id)
)
  ENGINE = InnoDB;

CREATE INDEX evolutions_pokemon_pokemon_id_fk
  ON evolutions (from_id);

CREATE INDEX evolutions_pokemon_to_id_fk
  ON evolutions (to_id);

CREATE TABLE games
(
  game_id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name          TEXT NULL,
  generation_id INT  NULL
)
  ENGINE = InnoDB;

CREATE INDEX game_generations_generation_id_fk
  ON games (generation_id);

ALTER TABLE attackeffects
  ADD CONSTRAINT attackeffects_games_game_id_fk
FOREIGN KEY (game_id) REFERENCES games (game_id);

ALTER TABLE attackswitheffects
  ADD CONSTRAINT attackswitheffects_games_game_id_fk
FOREIGN KEY (game_id) REFERENCES games (game_id);

ALTER TABLE catchrates
  ADD CONSTRAINT catchrates_games_game_id_fk
FOREIGN KEY (game_id) REFERENCES games (game_id);

ALTER TABLE egggroups
  ADD CONSTRAINT egggroups_games_game_id_fk
FOREIGN KEY (game_id) REFERENCES games (game_id);

CREATE TABLE generations
(
  generation_id INT  NOT NULL
    PRIMARY KEY,
  name          TEXT NULL,
  CONSTRAINT Generations_generation_id_uindex
  UNIQUE (generation_id)
)
  ENGINE = InnoDB;

ALTER TABLE abilities
  ADD CONSTRAINT abilities_generations_generation_id_fk
FOREIGN KEY (generation_id) REFERENCES generations (generation_id);

ALTER TABLE attacknames
  ADD CONSTRAINT attacknames_generations_generation_id_fk
FOREIGN KEY (generation_id) REFERENCES generations (generation_id);

ALTER TABLE attacks
  ADD CONSTRAINT attacks_generations_generation_id_fk
FOREIGN KEY (generation_id) REFERENCES generations (generation_id);

ALTER TABLE games
  ADD CONSTRAINT game_generations_generation_id_fk
FOREIGN KEY (generation_id) REFERENCES generations (generation_id);

CREATE TABLE itemnames
(
  item_id     INT        NOT NULL,
  language    VARCHAR(7) NOT NULL,
  name        TEXT       NULL,
  description TEXT       NULL,
  PRIMARY KEY (item_id, language)
)
  ENGINE = InnoDB;

CREATE TABLE items
(
  item_id   INT AUTO_INCREMENT
    PRIMARY KEY,
  game_id   INT  NULL,
  item_type TEXT NULL,
  CONSTRAINT items_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX items_games_game_id_fk
  ON items (game_id);

ALTER TABLE itemnames
  ADD CONSTRAINT itemnames_items_item_id_fk
FOREIGN KEY (item_id) REFERENCES items (item_id);

CREATE TABLE pokemon
(
  dex_number INT DEFAULT '0' NOT NULL,
  form       INT DEFAULT '0' NOT NULL,
  pokemon_id INT AUTO_INCREMENT
    PRIMARY KEY
)
  ENGINE = InnoDB;

CREATE INDEX pokemon_dexNumber_index
  ON pokemon (dex_number);

CREATE INDEX pokemon_formNumber_index
  ON pokemon (form);

ALTER TABLE catchrates
  ADD CONSTRAINT catchrates_pokemon_pokemon_id_fk
FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id);

ALTER TABLE egggroups
  ADD CONSTRAINT egggroups_pokemon_pokemon_id_fk
FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id);

ALTER TABLE evolutions
  ADD CONSTRAINT evolutions_pokemon_pokemon_id_fk
FOREIGN KEY (from_id) REFERENCES pokemon (pokemon_id);

ALTER TABLE evolutions
  ADD CONSTRAINT evolutions_pokemon_to_id_fk
FOREIGN KEY (to_id) REFERENCES pokemon (pokemon_id);

CREATE TABLE pokemonabilities
(
  pokemon_id     INT NOT NULL,
  game_id        INT NOT NULL,
  ability_1      INT NULL,
  ability_2      INT NULL,
  ability_hidden INT NULL,
  PRIMARY KEY (pokemon_id, game_id),
  CONSTRAINT pokemonabilities_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemonabilities_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id),
  CONSTRAINT pokemonabilities_abilities_ability_id_fk
  FOREIGN KEY (ability_1) REFERENCES abilities (ability_id),
  CONSTRAINT pokemonabilities_abilities_ability_2_id_fk
  FOREIGN KEY (ability_2) REFERENCES abilities (ability_id),
  CONSTRAINT pokemonabilities_abilities_ability_hidden_ability_id_fk
  FOREIGN KEY (ability_hidden) REFERENCES abilities (ability_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemonabilities_games_game_id_fk
  ON pokemonabilities (game_id);

CREATE INDEX pokemonabilities_abilities_ability_id_fk
  ON pokemonabilities (ability_1);

CREATE INDEX pokemonabilities_abilities_ability_2_id_fk
  ON pokemonabilities (ability_2);

CREATE INDEX pokemonabilities_abilities_ability_hidden_ability_id_fk
  ON pokemonabilities (ability_hidden);

CREATE TABLE pokemonattacksevent
(
  pokemon_id INT NULL,
  attack_id  INT NULL,
  game_id    INT NULL,
  CONSTRAINT pokemonattacksevent_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemonattacksevent_attacks_attack_id_fk
  FOREIGN KEY (attack_id) REFERENCES attacks (attack_id),
  CONSTRAINT pokemonattacksevent_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemonattacksevent_pokemon_pokemon_id_fk
  ON pokemonattacksevent (pokemon_id);

CREATE INDEX pokemonattacksevent_attacks_attack_id_fk
  ON pokemonattacksevent (attack_id);

CREATE INDEX pokemonattacksevent_games_game_id_fk
  ON pokemonattacksevent (game_id);

CREATE TABLE pokemonattackslevel
(
  pokemon_id INT NULL,
  attack_id  INT NULL,
  game_id    INT NULL,
  level      INT NULL,
  CONSTRAINT pokemonlevelattacks_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemonlevelattacks_attacks_attack_id_fk
  FOREIGN KEY (attack_id) REFERENCES attacks (attack_id),
  CONSTRAINT pokemonlevelattacks_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemonlevelattacks_pokemon_pokemon_id_fk
  ON pokemonattackslevel (pokemon_id);

CREATE INDEX pokemonlevelattacks_attacks_attack_id_fk
  ON pokemonattackslevel (attack_id);

CREATE INDEX pokemonlevelattacks_games_game_id_fk
  ON pokemonattackslevel (game_id);

CREATE TABLE pokemonattackstm
(
  pokemon_id INT NULL,
  attack_id  INT NULL,
  game_id    INT NULL,
  tm_number  INT NULL,
  CONSTRAINT pokemontmattacks_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemontmattacks_attacks_attack_id_fk
  FOREIGN KEY (attack_id) REFERENCES attacks (attack_id),
  CONSTRAINT pokemontmattacks_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemontmattacks_pokemon_pokemon_id_fk
  ON pokemonattackstm (pokemon_id);

CREATE INDEX pokemontmattacks_attacks_attack_id_fk
  ON pokemonattackstm (attack_id);

CREATE INDEX pokemontmattacks_games_game_id_fk
  ON pokemonattackstm (game_id);

CREATE TABLE pokemonattackstutor
(
  pokemon_id INT NULL,
  attack_id  INT NULL,
  game_id    INT NULL,
  CONSTRAINT pokemonattackstutor_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemonattackstutor_attacks_attack_id_fk
  FOREIGN KEY (attack_id) REFERENCES attacks (attack_id),
  CONSTRAINT pokemonattackstutor_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemonattackstutor_pokemon_pokemon_id_fk
  ON pokemonattackstutor (pokemon_id);

CREATE INDEX pokemonattackstutor_attacks_attack_id_fk
  ON pokemonattackstutor (attack_id);

CREATE INDEX pokemonattackstutor_games_game_id_fk
  ON pokemonattackstutor (game_id);

CREATE TABLE pokemonbasestats
(
  pokemon_id    INT NOT NULL,
  hp            INT NULL,
  atk           INT NULL,
  def           INT NULL,
  spatk         INT NULL,
  spdef         INT NULL,
  speed         INT NULL,
  generation_id INT NOT NULL,
  PRIMARY KEY (pokemon_id, generation_id),
  CONSTRAINT pokemonstats_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemonstats_Generations_generation_id_fk
  FOREIGN KEY (generation_id) REFERENCES generations (generation_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemonstats_generation_id_index
  ON pokemonbasestats (generation_id);

CREATE TABLE pokemondata
(
  pokemon_id  INT NOT NULL,
  base_height INT NULL,
  base_weight INT NULL,
  color       INT NULL,
  maleratio   INT NULL,
  game_id     INT NOT NULL,
  PRIMARY KEY (pokemon_id, game_id),
  CONSTRAINT pokemondata_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemondata_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemondata_games_game_id_fk
  ON pokemondata (game_id);

CREATE TABLE pokemonnames
(
  pokemon_id INT        NOT NULL,
  language   VARCHAR(7) NOT NULL,
  name       TEXT       NOT NULL,
  form_name  TEXT       NULL,
  PRIMARY KEY (pokemon_id, language),
  CONSTRAINT pokemonnames_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id)
)
  ENGINE = InnoDB;

CREATE TABLE pokemontypes
(
  pokemon_id INT NOT NULL,
  game_id    INT NOT NULL,
  type_1     INT NULL,
  type_2     INT NULL,
  PRIMARY KEY (pokemon_id, game_id),
  CONSTRAINT pokemontypes_pokemon_pokemon_id_fk
  FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
  CONSTRAINT pokemontypes_games_game_id_fk
  FOREIGN KEY (game_id) REFERENCES games (game_id)
)
  ENGINE = InnoDB;

CREATE INDEX pokemontypes_games_game_id_fk
  ON pokemontypes (game_id);


