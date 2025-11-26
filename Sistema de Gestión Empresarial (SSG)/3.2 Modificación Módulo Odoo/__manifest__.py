# byAlexLR

{
    'name': 'Contactos: Modificado', # Nombre del módulo
    'version': '1.0', # Versión del módulo
    'author': 'byAlexLR', # Autor del módulo
    'category': 'Custom', # Categoría del módulo
    'summary': 'Nivel de cliente y Edad calculada', # Resumen del módulo
    'depends': ['base', 'contacts'], # Dependencias del módulo
    'data': [
        'views/res_partner_views.xml', # Vista personalizada de res.partner
    ],
    'installable': True, # Indica si el módulo es instalable
    'application': False, # Indica si el módulo es una aplicación
}