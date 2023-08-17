import React from "react";
import Parchment from "@component/Parchment";
import MatchHistoryTableBody from "./MatchHistoryTableBody";

const MatchHistoryTable = () => {
  return (
    <Parchment
      style={{
        width: "100%",
        height: "95%",
        display: "block",
        overflow: "auto",
      }}>
      <table
        className="w-full table-auto border-collapse"
        style={{
          fontFamily: "NexonGothic",
          fontSize: "20px",
          fontWeight: "bold",
        }}>
        <caption
          className="caption-top"
          style={{
            fontFamily: "Unchained-RegularShadow",
            fontSize: "55px",
            fontWeight: "normal",
          }}>
          Match History
        </caption>
        <thead className="sticky top-0">
          <tr className="bg-red-950 text-yellow-100">
            <th className="w-1/5">결과</th>
            <th className="w-1/6 ">모드</th>
            <th>상대의 닉네임</th>
            <th className="w-1/6">현상금</th>
            <th className="w-1/6">변동</th>
          </tr>
        </thead>
        <MatchHistoryTableBody />
      </table>
    </Parchment>
  );
};

export default MatchHistoryTable;
