import React from "react";
import { history } from "./test";
import { getRatingUpDown, getTicketImg } from "../api/table";
import Parchment from "@component/Parchment";

const MatchHistoryTable = () => {
  const table = history;

  const tableRow = table.map((match) => (
    <tr key={match.id} className="h-20 text-center border-y-2 border-black">
      <td
        className="w-1/5 text-center"
        style={{
          fontFamily: "Unchained-RegularShadow",
          fontSize: "40px",
          fontWeight: "normal",
        }}>
        {match.res}
      </td>
      <td>
        <img src={getTicketImg(match.mode)} alt="tier" />
      </td>
      <td>{match.name}</td>
      <td className="w-1/6">{getRatingUpDown(match.rated)}</td>
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
            fontSize: "40px",
            fontWeight: "normal",
          }}>
          Match History
        </caption>
        <tbody>{tableRow}</tbody>
      </table>
    </Parchment>
  );
};

export default MatchHistoryTable;
