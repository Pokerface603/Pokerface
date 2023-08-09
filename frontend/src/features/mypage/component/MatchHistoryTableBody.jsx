import React, { useEffect } from "react";
import { getRatingUpDown, getTicketImg } from "./utils/table";
import { getHistoryTableRow } from "../api/getTableRow";
import { useState } from "react";
import { useRef } from "react";

const MatchHistoryTableBody = () => {
  const [tableBody, setTableBody] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [page, setPage] = useState(1);

  const loaderRef = useRef(null);

  useEffect(() => {
    fetchData();
  }, [page]);

  useEffect(() => {
    const observer = new IntersectionObserver(handleObserver, {
      root: null,
      rootMargin: "0px",
      threshold: 1.0,
    });

    if (loaderRef.current) {
      observer.observe(loaderRef.current);
    }

    return () => {
      if (loaderRef.current) {
        observer.unobserve(loaderRef.current);
      }
    };
  }, []);

  const fetchData = async () => {
    try {
      setIsLoading(true);
      const newData = await getHistoryTableRow(3);
      setTableBody((prevData) => [...prevData, ...newData]);
      setIsLoading(false);
    } catch (error) {
      console.log("error:", error);
      setIsLoading(false);
    }
  };

  const handleObserver = (entries) => {
    const target = entries[0];
    if (target.isIntersecting && !isLoading) {
      setPage((prevPage) => prevPage + 1);
    }
  };

  const makeTableRow = () => {
    if (tableBody.length > 0) {
      return tableBody.map((match) => (
        <tr
          key={match.historyId}
          className="h-20 text-center border-y-2 border-black">
          <td
            style={{
              fontFamily: "Unchained-RegularShadow",
              fontSize: "40px",
              fontWeight: "normal",
            }}>
            {match.result}
          </td>
          <td className="flex justify-center">
            <img
              className=" shadow-md shadow-slate-500 mt-2 mb-2"
              src={getTicketImg(match.gameMode)}
              alt="tier"
            />
          </td>
          <td>{match.opponentId}</td>
          <td>{match.postRating}</td>
          <td>{getRatingUpDown(match.postRating - match.preRating)}</td>
        </tr>
      ));
    } else
      return (
        <tr>
          <td colSpan="5">No data available</td>
        </tr>
      );
  };
  return (
    <tbody>
      {makeTableRow()}
      <tr ref={loaderRef}></tr>
      {isLoading && (
        <tr>
          <td>Loading..</td>
        </tr>
      )}
    </tbody>
  );
};

export default MatchHistoryTableBody;
