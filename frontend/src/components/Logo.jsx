const Logo = () => (
    <div className="mb-10 flex flex-col items-center lg:items-start">
      <div className="flex items-center gap-2">
        <div className="flex h-12 w-10 flex-col justify-center gap-1 rounded border-2 border-orange-500 p-1">
          <div className="h-1 w-full rounded-full bg-orange-500" />
          <div className="flex flex-1 flex-col gap-1 px-0.5">
            <div className="h-0.5 w-full bg-orange-500/30" />
            <div className="h-0.5 w-full bg-orange-500/30" />
            <div className="h-0.5 w-full bg-orange-500/30" />
          </div>
          <div className="h-1 w-full rounded-full bg-orange-500" />
        </div>
        <div className="flex flex-col leading-none">
          <span className="text-3xl font-bold italic tracking-tight">Dev</span>
          <span className="text-3xl font-black uppercase tracking-tighter text-orange-500">Burger</span>
        </div>
      </div>
    </div>
  );
  
  export default Logo;