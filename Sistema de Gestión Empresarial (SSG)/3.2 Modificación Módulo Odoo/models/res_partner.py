# byAlexLR

from odoo import models, fields, api # Importa los módulos necesarios de Odoo
from datetime import date # Importa date para manejar fechas

# Clase que extiende el modelo res.partner
class ResPartner(models.Model):
    _inherit = 'res.partner'  # Indica que se está extendiendo el modelo res.partner

    # --- EJERCICIO 2 ---
    # Campo de selección para el nivel de cliente
    customer_level = fields.Selection(
        [('gold', 'Oro'),
         ('silver', 'Plata'),
         ('bronze', 'Bronce')],
        string='Nivel de cliente', # Etiqueta del campo
        help="Clasificación del cliente (Oro/Plata/Bronce)" # Ayuda del campo
    )

    # --- EJERCICIO 3 ---
    birth_date = fields.Date(string='Fecha de nacimiento') # Campo para la fecha de nacimiento
    age = fields.Integer(string='Edad', compute='_compute_age', store=True) # Campo para la edad que se calcula

    # Método para calcular la edad basada en la fecha de nacimiento
    @api.depends('birth_date')
    def _compute_age(self):
        for record in self: # Itera sobre los registros
            if record.birth_date: # Verifica si la fecha de nacimiento está establecida
                record.age = date.today().year - record.birth_date.year # Calcula la edad
            else: # Si no hay fecha de nacimiento, la edad es 0
                record.age = 0