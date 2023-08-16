import React from "react";
import InitialRow from "./roundrows/InitialRow";
import RoundDivider from "./roundrows/RoundDivder";
import BetRow from "./roundrows/BetRow";
import { isMyTurn } from "./roundrows/betbox/isMyTurn";

const HistoryRounds = ({ roundLogResponses }) => {
  return (
    <div className={`grid grid-cols-1`}>
      {roundLogResponses !== null &&
        roundLogResponses.map((round, idx) => {
          return (
            <div className="w-full text-center" key={idx}>
              <RoundDivider
                roundNum={idx + 1}
                leftCardNum={round.myCardNum}
                rightCardNum={round.opponentCardNum}
              />
              <InitialRow
                myRemainChips={round.myRemainChips}
                opponentRemainChips={round.opponentRemainChips}
              />
              {round.turnLogResponses.map((betting, idx) => {
                return (
                  <div className="w-full" key={idx}>
                    <BetRow
                      isMyBet={isMyTurn(idx)}
                      betType={betting.betType}
                      bettingMoney={betting.bettingMoney}
                    />
                  </div>
                );
              })}
              <p
                className="animate-pulse"
                style={{
                  fontFamily: "BouWestern",
                  fontSize: "32px",
                  color:
                    round.result === "WIN"
                      ? "red"
                      : round.result === "LOSE"
                      ? "blue"
                      : "green",
                }}>
                {round.result}
              </p>
            </div>
          );
        })}
    </div>
  );
};

export default HistoryRounds;
