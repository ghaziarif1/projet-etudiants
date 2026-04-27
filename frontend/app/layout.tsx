import type { Metadata } from 'next'
import './globals.css'

export const metadata: Metadata = {
  title: 'Student Management',
  description: 'Manage students and departments',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className="bg-gray-100">
        <nav className="bg-blue-600 text-white p-4">
          <div className="container mx-auto flex justify-between">
            <h1 className="text-xl font-bold">Student Management</h1>
            <div className="space-x-4">
              <a href="/etudiants" className="hover:underline">Étudiants</a>
              <a href="/departements" className="hover:underline">Départements</a>
            </div>
          </div>
        </nav>
        <main className="container mx-auto p-4">
          {children}
        </main>
      </body>
    </html>
  )
}