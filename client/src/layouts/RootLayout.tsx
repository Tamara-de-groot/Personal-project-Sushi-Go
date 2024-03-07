 import { Outlet, useLocation } from "react-router-dom"

 export const RootLayout = () => {
     const { pathname } = useLocation();

     return <div className="flex flex-col h-screen ">
         <header className="bg-sogyo shadow-lg flex-row p-4">
             <nav className="pt-4 flex-1 flex flex-row justify-center gap-2">
             </nav>
         </header>
         <main className ="flex-1">
             <Outlet />
         </main>
     </div>
 };


