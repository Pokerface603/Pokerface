export const testData = {
  historyId: 2,
  gameMode: "NORMAL",
  gameLogResponse: {
    roundLogResponses: [
      {
        isMeFirst: true,
        myCardNum: 7,
        opponentCardNum: 18,
        myRemainChips: 30,
        opponentRemainChips: 30,
        seedMoney: 2,
        bettingLog: [
          { betType: "bet", betMoney: 22 },
          { betType: "bet", betMoney: 22 },

          { betType: "bet", betMoney: 22 },
          { betType: "bet", betMoney: 22 },

          { betType: "bet", betMoney: 22 },
          { betType: "bet", betMoney: 22 },

          { betType: "bet", betMoney: 22 },
          { betType: "call", betMoney: 22 },
        ],
        result: "LOSE",
      },
      {
        isMeFirst: false,
        myCardNum: 2,
        opponentCardNum: 11,
        myRemainChips: 7,
        opponentRemainChips: 53,
        seedMoney: 2,
        bettingLog: [
          { betType: "all-in", betMoney: 22 },
          { betType: "call", betMoney: 22 },
        ],
        result: "WIN",
      },
      {
        isMeFirst: true,
        myCardNum: 15,
        opponentCardNum: 6,
        myRemainChips: 14,
        opponentRemainChips: 46,
        seedMoney: 2,
        bettingLog: [
          { betType: "bet", betMoney: 22 },
          { betType: "call", betMoney: 22 },
        ],
        result: "LOSE",
      },
    ],
  },
  hostNickName: "국이",
  guestNickName: "시니",
};
