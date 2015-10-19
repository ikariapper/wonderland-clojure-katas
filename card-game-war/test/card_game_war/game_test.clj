(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-compare-cards
  (testing "the highest rank wins the cards in the round"
    (is (= "player1" (compare-cards 11 30))))
  (testing "queens are higher rank than jacks"
    (is (= "player2" (compare-cards 9 10))))
  (testing "kings are higher rank than queens"
    (is (= "player1" (compare-cards 11 10))))
  (testing "aces are higher rank than kings"
    (is (= "player2" (compare-cards 11 12))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= "player1" (compare-cards 13 0))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= "player2" (compare-cards 13 26))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= "player1" (compare-cards 39 26)))))

(deftest test-play-round
  (testing "player1 has empty deck after round"
    (is (= [nil [7 3]] (play-round [3] [7])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

