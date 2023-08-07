import React from "react";
import { history } from "./test";
import { getRatingUpDown, getTicketImg } from "../api/table";
import Parchment from "@component/Parchment";

const MatchHistoryTable = () => {
  const table = history;

  const makeTableRow = table.map((match) => (
    <tr key={match.id} className="h-20 text-center border-y-2 border-black">
      <td
        style={{
          fontFamily: "Unchained-RegularShadow",
          fontSize: "40px",
          fontWeight: "normal",
        }}>
        {match.res}
      </td>
      <td className="flex justify-center">
        <img
          className=" shadow-md shadow-slate-500 mt-2 mb-2"
          src={getTicketImg(match.mode)}
          alt="tier"
        />
      </td>
      <td>{match.name}</td>
      <td>{match.rating}</td>
      <td>{getRatingUpDown(match.rating - match.prevRating)}</td>
    </tr>
  ));

  return (
    <Parchment style={{ width: "100%", height: "95%", overflow: "scroll" }}>
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
        <thead>
          <tr className="bg-red-950 text-yellow-100">
            <th className="w-1/5">결과</th>
            <th className="w-1/6 ">모드</th>
            <th>상대의 닉네임</th>
            <th className="w-1/6">레이팅</th>
            <th className="w-1/6">변동</th>
          </tr>
        </thead>
        <tbody>{makeTableRow}</tbody>
      </table>
    </Parchment>
  );
};

export default MatchHistoryTable;
