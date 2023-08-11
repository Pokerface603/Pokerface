import React from "react";
import InitialRow from "./roundrows/InitialRow";
import RoundDivider from "./roundrows/RoundDivder";
import BetRow from "./roundrows/BetRow";
import { isMyTurn } from "./roundrows/betbox/isMyTurn";

const HistoryRounds = ({ roundLogResponses }) => {
  return (
    <div className={`grid grid-cols-1`}>
      {roundLogResponses.map((round, idx) => {
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
            {round.bettingLog.map((betting, idx) => {
              return (
                <div className="w-full" key={idx}>
                  <BetRow
                    isMyBet={isMyTurn(idx)}
                    betType={betting.betType}
                    bettingMoney={betting.betMoney}
                  />
                </div>
              );
            })}
            <p
              style={{
                fontFamily: "BouWestern",
                fontSize: "32px",
                color: round.result === "WIN" ? "red" : "blue",
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
