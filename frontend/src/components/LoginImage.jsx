const LoginImage = () => (
    <div className="hidden items-center justify-center bg-gray-50/50 lg:flex lg:w-1/2">
      <div className="relative h-full w-full max-w-2xl p-12">
        <div className="flex h-full w-full items-center justify-center">
          <img
            src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=1000&auto=format&fit=crop"
            alt="Delicioso Hambúrguer"
            className="h-auto w-full max-w-lg object-contain drop-shadow-2xl"
            onError={(e) => { e.target.src = 'https://via.placeholder.com/600x600?text=Dev+Burger'; }}
          />
        </div>
      </div>
    </div>
  );
  
  export default LoginImage;