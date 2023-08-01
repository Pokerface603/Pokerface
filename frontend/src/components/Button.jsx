const Button = ({ label, onClick, icon }) => {
  return (
    <div>
      <button onClick={onClick} type="submit" className="bg-orange-400 hover:bg-orange-500 text-white font-bold py-1 px-8">
        {icon}
        <span>{label}</span>
      </button>
    </div>
  );
};

export default Button;
