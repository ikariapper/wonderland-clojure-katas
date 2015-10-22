(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(defn deal []
  (zipmap [:player1 :player2] (partition 26 (shuffle (range 52)))))

;; fill in  tests for your game
(deftest test-compare-cards
  (testing "the highest rank wins the cards in the round"
    (is (= :player1 (compare-cards {:player1 11 :player2 30}))))
  (testing "queens are higher rank than jacks"
    (is (= :player2 (compare-cards {:player1 9 :player2 10}))))
  (testing "kings are higher rank than queens"
    (is (= :player1 (compare-cards {:player1 11 :player2 10}))))
  (testing "aces are higher rank than kings"
    (is (= :player2 (compare-cards {:player1 11 :player2 12}))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= :player1 (compare-cards {:player1 13 :player2 0}))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= :player2 (compare-cards {:player1 13 :player2 26}))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= :player1 (compare-cards {:player1 39 :player2 26})))))

(deftest test-play-round
  (testing "player1 has empty deck after round"
    (is (= {:player1 '() :player2 '(7 3)} (play-round {:player1 '(3) :player2 '(7)}))))
  (testing "player1 gains cards"
    (is (= {:player1 '(2 12 16) :player2 '(3)}(play-round {:player1 '(12 2) :player2 '(16 3)})))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= :player1 (play-game {:player1 '(26 23 51 12) :player2 '(9 12 34 11)})))
    (is (= :player1 (play-game {:player1 '(30 14 51 23) :player2 '(47 34 12 29)}))))
  (testing "test with randomly dealt cards"
    (is (= :player1 (play-game (deal))))))


